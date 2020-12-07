package service;

import java.util.List;

import org.apache.log4j.Logger;

import daos.EventResultDao;
import daos.EventResultDaoPostgres;
import pojos.EventResult;

public class EventResultServiceFullStack implements EventResultService {
	
	private static Logger log = Logger.getRootLogger();
	
	private EventResultDao resultDao = new EventResultDaoPostgres();

	@Override
	public List<EventResult> readResultGrade(int bencoId) {
		log.info("EventResult service: read event grade by benco Id");
		
		return resultDao.readGradeByBencoId(bencoId);
	}

	@Override
	public byte[] readAttachment(int depheadId) {
		log.info("EventResult service: read presentation");
		
		return resultDao.readAttachmentByDepheadId(depheadId);
	}

	@Override
	public void bencoApproveGrade(int requestId) {
		log.info("eventResult service: benco approving grade");
		resultDao.bencoApproveGrade(requestId);
		
	}

	@Override
	public void bencoDenyGrade(int requestId) {
	log.info("eventresult service: bneco denying grade");
	resultDao.bencoDenyGrade(requestId);
		
	}

}
