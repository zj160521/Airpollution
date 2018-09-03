package com.xf.activiti;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xf.entity.ActRecords;
import com.xf.entity.Company;
import com.xf.service.ActRecordService;

@Component
public class ActivitiWorkFlow {

	@Autowired
	private ActRecordService dao;
	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**部署流程定义（从inputStream）*/
	public void deploymentProcessDefinition_inputStream(){
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream("sequenceFlow.bpmn");
		InputStream inputStreamPng = this.getClass().getResourceAsStream("sequenceFlow.png");
		Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
						.createDeployment()//创建一个部署对象
						.name("工作流程")//添加部署的名称
						.addInputStream("sequenceFlow.bpmn", inputStreamBpmn)//
						.addInputStream("sequenceFlow.png", inputStreamPng)//
						.deploy();//完成部署
		System.out.println("部署ID："+deployment.getId());//
		System.out.println("部署名称："+deployment.getName());//
	}
	
	/**启动流程实例*/
	public void startProcessInstance(int enterpriceId,int accountId,int fillyear){
		//流程定义的key
		String processDefinitionKey = "workFlow";
		System.out.println("*****"+processEngine);
		ProcessInstance pi = processEngine.getRuntimeService()//与正在执行的流程实例和执行对象相关的Service
						.startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		System.out.println("流程实例ID:"+pi.getId());//流程实例ID    101
		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID   helloworld:1:4
		ActRecords ar=new ActRecords();
		ar.setProcessInstanceId(pi.getId());
		ar.setEnterpriceId(enterpriceId);
		ar.setAccountId(accountId);
		ar.setUserId(0);
		ar.setFillyear(fillyear);
		dao.add(ar);
	}
	
	/**查询审核进度*/
	public List<Company> findAssignee(List<Company> list,int fillyear){
		for(Company c:list){
			ActRecords ar=new ActRecords();
			ar.setEnterpriceId(c.getId());
			ar.setFillyear(fillyear);
			//查询出当年所有的工作流关系
			List<ActRecords> ars=dao.getByAct(ar);
			for(ActRecords a:ars){
				List<Task> tasks = processEngine.getTaskService()//与正在执行的任务管理相关的Service
						.createTaskQuery()//创建任务查询对象
						/**查询条件（where部分）*/
//						.taskAssignee(assignee)//指定个人任务查询，指定办理人
//						.taskCandidateUser(candidateUser)//组任务的办理人查询
//						.processDefinitionId(processDefinitionId)//使用流程定义ID查询
						.processInstanceId(a.getProcessInstanceId())//使用流程实例ID查询
//						.executionId(executionId)//使用执行对象ID查询
						/**排序*/
						.orderByTaskCreateTime().asc()//使用创建时间的升序排列
						/**返回结果集*/
//						.singleResult()//返回惟一结果集
//						.count()//返回结果集的数量
//						.listPage(firstResult, maxResults);//分页查询
						.list();//返回列表
				for(Task t:tasks){
					c.setAssignee(t.getAssignee());
				}
			}
		}
		return list;
	}
	
	/**查询当前人的个人任务*/
	public List<ActRecords> findMyPersonalTask(String assignee,int fillyear){
//		String assignee = "市州管理员";
//		String processInstanceId="501";
		List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
						.createTaskQuery()//创建任务查询对象
						/**查询条件（where部分）*/
						.taskAssignee(assignee)//指定个人任务查询，指定办理人
//						.taskCandidateUser(candidateUser)//组任务的办理人查询
//						.processDefinitionId(processDefinitionId)//使用流程定义ID查询
//						.processInstanceId(processInstanceId)//使用流程实例ID查询
//						.executionId(executionId)//使用执行对象ID查询
						/**排序*/
						.orderByTaskCreateTime().asc()//使用创建时间的升序排列
						/**返回结果集*/
//						.singleResult()//返回惟一结果集
//						.count()//返回结果集的数量
//						.listPage(firstResult, maxResults);//分页查询
						.list();//返回列表
		List<ActRecords> res=new ArrayList<ActRecords>();
		if(list!=null && list.size()>0){
			ActRecords ar=new ActRecords();
			ar.setFillyear(fillyear);
			//查询出当年所有的工作流关系
			List<ActRecords> ars=dao.getByAct(ar);
			//匹配当前管理员待处理的工作流
			for(ActRecords atc:ars){
				for(Task task:list){
					if(atc.getProcessInstanceId().equals(task.getProcessInstanceId())){
						//取到accountid或者enterpriceId进行查询并返回
						atc.setTaskId(task.getId());
						res.add(atc);
					}
				}
			}
//			for(Task task:list){
//				System.out.println("任务ID:"+task.getId());
//				System.out.println("任务名称:"+task.getName());
//				System.out.println("任务的创建时间:"+task.getCreateTime());
//				System.out.println("任务的办理人:"+task.getAssignee());
//				System.out.println("流程实例ID："+task.getProcessInstanceId());
//				System.out.println("执行对象ID:"+task.getExecutionId());
//				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
//				System.out.println("########################################################");
//			}
		}
		return res;
	}
	
	/**传入公司ID完成我的不通过任务*/
	public void completeTaskByCompanyId(int companyId,int fillyear,String remark){
		ActRecords ar=new ActRecords();
		ar.setEnterpriceId(companyId);
		ar.setFillyear(fillyear);
		//查询出当年所有的工作流关系
		List<ActRecords> ars=dao.getByAct(ar);
		for(ActRecords a:ars){
			List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
					.createTaskQuery()//创建任务查询对象
					/**查询条件（where部分）*/
//					.taskAssignee(assignee)//指定个人任务查询，指定办理人
//					.taskCandidateUser(candidateUser)//组任务的办理人查询
//					.processDefinitionId(processDefinitionId)//使用流程定义ID查询
					.processInstanceId(a.getProcessInstanceId())//使用流程实例ID查询
//					.executionId(executionId)//使用执行对象ID查询
					/**排序*/
					.orderByTaskCreateTime().asc()//使用创建时间的升序排列
					/**返回结果集*/
//					.singleResult()//返回惟一结果集
//					.count()//返回结果集的数量
//					.listPage(firstResult, maxResults);//分页查询
					.list();//返回列表
			for(Task t:list){
				completeMyPersonalTask(t.getId(),"不通过",a.getProcessInstanceId(),remark);
			}
		}
	}
	
	/**完成我的任务*/
	public void completeMyPersonalTask(String taskId,String message,String processInstanceId,String message2){
		
		//完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线，对应exclusiveGateWay.bpmn文件中${money>1000}
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("message", message);
		if(message.equals("不通过")){
			/**使用流程变量设置不通过原因，用来传递业务参数*/
			processEngine.getRuntimeService()//
							.setVariable(processInstanceId, "不通过原因", message2);
		}
		processEngine.getTaskService()//与正在执行的任务管理相关的Service
					.complete(taskId,variables);
		System.out.println("完成任务：任务ID："+taskId);
	}
	
	
	
	@Test
	public void completeMyPersonalTask1(){
		//任务ID
		String taskId = "1303";
		String message="不通过";
		String message2="填报不正确！";
		String processInstanceId="1210";
		//完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线，对应exclusiveGateWay.bpmn文件中${money>1000}
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("message", message);
		if(message.equals("不通过")){
			/**使用流程变量设置不通过原因，用来传递业务参数*/
			processEngine.getRuntimeService()//
							.setVariable(processInstanceId, "不通过原因", message2);
		}
		processEngine.getTaskService()//与正在执行的任务管理相关的Service
					.complete(taskId,variables);
		System.out.println("完成任务：任务ID："+taskId);
	}
	
	/**查询当前人的个人任务*/
	@Test
	public void findMyPersonalTask2(){
//		String assignee = "市州管理员";
		String processInstanceId="1210";
		
		List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
						.createTaskQuery()//创建任务查询对象
						/**查询条件（where部分）*/
//						.taskAssignee(assignee)//指定个人任务查询，指定办理人
//						.taskCandidateUser(candidateUser)//组任务的办理人查询
//						.processDefinitionId(processDefinitionId)//使用流程定义ID查询
						.processInstanceId(processInstanceId)//使用流程实例ID查询
//						.executionId(executionId)//使用执行对象ID查询
						/**排序*/
						.orderByTaskCreateTime().asc()//使用创建时间的升序排列
						/**返回结果集*/
//						.singleResult()//返回惟一结果集
//						.count()//返回结果集的数量
//						.listPage(firstResult, maxResults);//分页查询
						.list();//返回列表
		if(list!=null && list.size()>0){
			for(Task task:list){
				System.out.println("任务ID:"+task.getId());
				System.out.println("任务名称:"+task.getName());
				System.out.println("任务的创建时间:"+task.getCreateTime());
				System.out.println("任务的办理人:"+task.getAssignee());
				System.out.println("流程实例ID："+task.getProcessInstanceId());
				System.out.println("执行对象ID:"+task.getExecutionId());
				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
				System.out.println("########################################################");
			}
		}
		String value = (String) processEngine.getRuntimeService()//
				.getVariable(processInstanceId, "不通过原因");
		System.out.println("不通过原因："+value);
	}
}
