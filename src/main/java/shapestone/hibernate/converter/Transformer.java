package shapestone.hibernate.converter;

import shapestone.hibernate.VO.CertificateVO;
import shapestone.hibernate.model.Certificate;

public class Transformer {
	
	public static Certificate covertVOToEntity (CertificateVO vo) {
		Certificate cert = new Certificate();
		cert.setCertificateId(vo.getCertificateId());
		cert.setCost(vo.getCost());
		cert.setCourseName(vo.getCourseName());
		cert.setDateOfCertificationDone(vo.getDateOfCertificationDone());
		cert.setInstitutionName(vo.getInstitutionName());
		return cert;
	}

}
