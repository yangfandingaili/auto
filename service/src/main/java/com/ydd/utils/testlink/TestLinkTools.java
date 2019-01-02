package com.ydd.utils.testlink;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import com.ydd.utils.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class TestLinkTools {
	private static Log log=new Log(TestLinkTools.class);
	/*testlink调用核心类*/
	private static TestLinkAPI testlinkApi = null;
	
	private TestPlan testPlan;
	
	private TestCase[] ts;
	/*testlink 计划名*/
	private String planName;
	/*testlink项目名*/
	private String projectName;
	/*个人签名*/
	private String devKey;
	/*testlink 调用地址*/
	private String url;
	/*构建名称*/
	private String buildName;
	/*平台名称*/
	private String platformName;
	public TestLinkTools(String planName, String projectName, String devKey, String url, String buildName) throws TestLinkAPIException, MalformedURLException {
		this.planName = planName;
		this.projectName = projectName;
		this.devKey = devKey;
		this.url = url;
		this.buildName = buildName;
//		this.platformName = platformName;
		getTestLinkApi();
		setTestPlan();
		
	}
	/**
	 * 获取testlinkapi接口调用对象
	 * @return
	 * @throws TestLinkAPIException
	 * @throws MalformedURLException
	 */
	private TestLinkAPI getTestLinkApi() throws TestLinkAPIException, MalformedURLException{
		if(null==testlinkApi){
			testlinkApi = new TestLinkAPI(new URL(url), devKey);
		}
		return testlinkApi;
	}
	public void getCases(){
		testPlan = testlinkApi.getTestPlanByName(planName, projectName);
		ts = testlinkApi.getTestCasesForTestPlan(testPlan.getId(), null, null, null, null, null, null, null, ExecutionType.AUTOMATED, null, null);
	}
	private void setTestPlan(){
		this.testPlan = testlinkApi.getTestPlanByName(planName, projectName);
	}
	
	public TestCase[] getTs() {
		return ts;
	}
	public void setTs(TestCase[] ts) {
		this.ts = ts;
	}
	/**
	 * 连接testlink执行测试用例
	 * @param caseId
	 * @param status
	 */
	public void excuteTestCase(Integer caseId,int status){
		
		switch (status) {
//		case 1:testlinkApi.reportTCResult(caseId, null, testPlan.getId(), ExecutionStatus.PASSED, null, null, buildName, "自动化测试备注", null, null, null, platformName, null, null);
		case 1 :testlinkApi.reportTCResult(caseId, null, testPlan.getId(), ExecutionStatus.PASSED, null, buildName, "自动化测试备注", null, null, null, platformName, null, null);
			break;
		case 2 :testlinkApi.reportTCResult(caseId, null, testPlan.getId(), ExecutionStatus.FAILED, null, buildName, "自动化测试备注", null, null, null, platformName, null, null);
		default:
			break;
		}
	}
	
	
	
}
