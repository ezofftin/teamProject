package ez.web.bbs;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/cipherTest")
	public void chiperTest() {
		String rawPassword = "test1234"; // 인코딩 전
		String encodePassword1;
		String encodePassword2;
		
		encodePassword1 = pwEncoder.encode(rawPassword);
		encodePassword2 = pwEncoder.encode(rawPassword);
		
		// 인코딩된 패스워드 출력
		System.out.println("encodePassword1 : " + encodePassword1);
		System.out.println("encodePassword2 : " + encodePassword2);
		
		String pwd1 = "test1234";
		String pwd2 = "abcd";
		
		System.out.println("패스워드 검증 :" + pwEncoder.matches(pwd1, encodePassword1));
		System.out.println("패스워드 검증 :" + pwEncoder.matches(pwd2, encodePassword1));
		
		
		
		
	}
	
}
