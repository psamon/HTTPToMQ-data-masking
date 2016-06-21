package com.anz.HttptoMQ.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.junit.Test;

import com.anz.HttpToMQ.logging.AppLogMask;
import com.anz.HttpToMQ.transform.pojo.NumbersInput;
import com.anz.common.compute.LogMaskUtils;
import com.anz.common.transform.TransformUtils;

public class LogMaskTest {
	
	private static final Logger logger = LogManager.getLogger();

	@Test
	public void testLogMask() throws Exception {
		String message = "16:42:17.383 [Thread-92] INFO  ApplicationLogger - {\"terminal\":\"Input\",\"service\":{\"serviceName\":\"HttpToHttp-app\",\"messageFlowName\":\"Main\"},\"broker\":{\"brokerName\":\"TESTNODE_root\",\"queueManagerName\":\"QMGR\",\"region\":\"AU\",\"classValue\":\"appliance.spaau020hwt01\",\"forwarder\":\"DRP\",\"hostName\":\"dpaau020hwt01.apps.anz\"},\"timestamp\":\"Jun1, 2016 4:42:17 PM\",\"message\":\"{ \"left\":3, \"right\":6 }\",\"incidentArea\":\"GAMS\"}";
		String expectedMessage = "16:42:17.383 [Thread-92] INFO  ApplicationLogger - {\"terminal\":\"XXXXX\",\"service\":{\"serviceName\":\"HttpToHttp-app\",\"messageFlowName\":\"Main\"},\"broker\":{\"brokerName\":\"TESTNODE_root\",\"queueManagerName\":\"QMGR\",\"region\":\"XX\",\"classValue\":\"appliance.spaau020hwt01\",\"forwarder\":\"DRP\",\"hostName\":\"dpaau020hwt01.apps.anz\"},\"timestamp\":\"Jun1, 2016 4:42:17 PM\",\"message\":\"{ \"left\":3, \"right\":6 }\",\"incidentArea\":\"GAMS\"}";
		List<String> regexArray = new ArrayList<String>();
		regexArray.add("(?<=region\":\")([^\"]*)");
		regexArray.add("(?<=terminal\":\")([^\"]*)");
		char mask = 'X';
		logger.info("original message = {}", message);
		
		
		message = LogMaskUtils.mask(regexArray, message, mask);
		
		logger.info("masked message = {}", message);
		logger.info("expected message = {}", expectedMessage);
		assertEquals(expectedMessage, message);
		logger.info(message);
		
	}

}
