package net.poundex.etch.google

import net.poundex.etch.gmail.GoogleLoginService
import org.springframework.beans.factory.annotation.Autowired

class GoogleLoginController
{
	@Autowired
	GoogleLoginService googleLoginService

	def login(GoogleAccount googleAccount)
	{
		String redirectURL = googleLoginService.beginAuthorise(googleAccount)
		redirect url: redirectURL
	}

	def auth()
	{
		if ( ! params['code']) return render(params['error'])
		GoogleAccount account = googleLoginService.completeAuthorise(params['code'])

		flash.message = "Auth completed"
		redirect controller: 'googleAccount', action: 'show', id: account.id
	}

}
