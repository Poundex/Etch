class UrlMappings {

    static mappings = {
        "/dashboard/"(controller: "dashboard", action: "index")
        "/dashboard/index"(controller: "dashboard", action: "index")
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
