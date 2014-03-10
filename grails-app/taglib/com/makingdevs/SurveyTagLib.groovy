package com.makingdevs

class SurveyTagLib {
    
  def showSurveyForThisInstance = { attrs, body ->
    def listSurveyAvailable=Survey.list()
    def surveyLinks = SurveyPerInstanceLink
      .findAllWhere(type:attrs.instance.class.getSimpleName(),
      surveyPerInstanceRef:attrs.instance.id)
    out << render(template:"/surveyPerInstance/surveyForThisInstance", 
      model:[listSurveyAvailable:listSurveyAvailable,
            instance:attrs.instance,
            surveyLinks:surveyLinks], plugin:"surveyable")
  }
}