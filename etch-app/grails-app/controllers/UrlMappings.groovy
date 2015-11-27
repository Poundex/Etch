class UrlMappings {

    static mappings = {
        "/dashboard/$name"(controller: 'dashboard', action: 'dashboard')

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
