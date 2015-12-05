package net.poundex.etch.google

import net.poundex.etch.gmail.GoogleLoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

class GoogleLoginController
{
	@Autowired @Qualifier("googleLoginServiceProxy")
	GoogleLoginService googleLoginServiceProxy

	def login(GoogleAccount googleAccount)
	{
		String redirectURL = googleLoginServiceProxy.beginAuthorise(googleAccount)
		redirect url: redirectURL
	}

	def auth()
	{
		if ( ! params['code']) return render(params['error'])
		GoogleAccount account = googleLoginServiceProxy.completeAuthorise(params['code'])

		flash.message = "Auth completed"
		redirect controller: 'googleAccount', action: 'show', id: account.id
	}

}
