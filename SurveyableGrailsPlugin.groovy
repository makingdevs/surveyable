class SurveyableGrailsPlugin {
    def version = "0.1.3"
    def grailsVersion = "2.3 > *"
    def pluginExcludes = [
        "grails-app/views/error.gsp",
        "grails-app/controllers/com/makingdevs/UserTestController.groovy",
        "grails-app/domain/com/makingdevs/UserTest.groovy",
        "grails-app/domain/com/makingdevs/AnotherUserTest.groovy",
        "grails-app/views/userTest/*"

    ]


    def title = "Surveyable Plugin" 
    def author = "Jorge Acosta Lemus"
    def authorEmail = "jorge@makingdevs.com"
    def description = '''\
That plugin add survey for evaluate something
'''

    def documentation = "https://github.com/makingdevs/Surveyable"
    def license = "APACHE"
    def organization = [ name: "MakingDevs", url: "http://makingdevs.com/" ]

    def developers = [
      [ name: "Jorge Acosta Lemus", email: "jorge@makingdevs.com" ],
      [ name: "Sergio Rodríguez", email: "sergio@makingdevs.com" ],
      [ name: "Felipe Juárez", email: "felipe@makingdevs.com" ],
      [ name: "Rodrigo Martínez", email: "rockdrigo.mtz@gmail.com" ],
      [ name: "José Juan Reyes", email: "juan@makingdevs.com" ],
      [ name: "Marlen Rodríguez Reyes", email: "marlen@makingdevs.com"]
    ]

    def issueManagement = [ system: "GITHUB", url: "https://github.com/makingdevs/surveyable/issues" ]

    def scm = [ url: "https://github.com/makingdevs/surveyable" ]


    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { ctx ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }
}
