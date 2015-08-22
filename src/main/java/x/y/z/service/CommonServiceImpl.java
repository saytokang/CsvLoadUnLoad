package x.y.z.service;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("commonService")
public class CommonServiceImpl implements CommonService{

	@Resource(name="messageSource")
	private MessageSource messageSource;

	@Override
	public String getMessage(String code, Object[] args) {

		return messageSource.getMessage(code, args, Locale.getDefault());
	}
	
}
