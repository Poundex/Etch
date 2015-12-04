package net.poundex.etch.dolphin

import groovy.util.logging.Log
import org.codehaus.groovy.reflection.ReflectionUtils
import org.opendolphin.core.ModelStoreEvent
import org.opendolphin.core.server.ServerDolphin
import org.opendolphin.core.server.action.DolphinServerAction
import org.opendolphin.core.server.comm.ActionRegistry
import org.springframework.beans.factory.annotation.Autowired
import sun.reflect.misc.MethodUtil

import javax.annotation.PostConstruct

/**
 * Created by poundex on 01/12/15.
 */
@Log
class DolphinConfiguration
{
	@Autowired
	ServerDolphin serverDolphin

	@Autowired
	List<DolphinService> dolphinServices

	@PostConstruct
	public void init()
	{
		serverDolphin.registerDefaultActions()
		serverDolphin.register { registry ->
			dolphinServices.each { service ->
				Class realClass = service.class.methods.find { it.name == 'getTargetClass' }.invoke(service)
				MethodUtil.getMethods(realClass).each { m ->
					DolphinAction action = m.getAnnotation(DolphinAction)
					if (action)
						registry.register(action.value(), service.&"${m.name}")

					DolphinListener listener = m.getAnnotation(DolphinListener)
					if (listener)
						serverDolphin.addModelStoreListener(listener.value(), service.&"${m.name}")
				}
			}
		}
	}
}
