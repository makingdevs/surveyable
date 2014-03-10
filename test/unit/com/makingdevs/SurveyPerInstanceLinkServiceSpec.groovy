package com.makingdevs

import grails.test.mixin.TestFor
import spock.lang.*

@TestFor(SurveyPerInstanceLinkService)
@Mock([UserTest,Survey,SurveyPerInstance,SurveyPerInstanceService,SurveyPerInstanceLink,AnotherUserTest])
class SurveyPerInstanceLinkServiceSpec extends Specification {

  def "Crear una instancia de survey para una clase que implementa la interfaz Surveyable"() {
    given:"Un usuario previamente creado y un survey existente"
      def userTest = new UserTest().save(validate:false)
      new Survey().save(validate:false)
    when:"Asignamos una instancia de survey a un usuario"
      def surveyPerInstanceLink = service.createSurveyPerInstance(userTest,1L)
    then:
      surveyPerInstanceLink.id > 0 // Se crea el vínculo
      surveyPerInstanceLink.surveyPerInstance.id > 0 // Se crea la instancia a la ref
      surveyPerInstanceLink.surveyPerInstance.survey.id == 1L // Se asigna el id de cuestionario
      surveyPerInstanceLink.type == "UserTest" // Se identifica la clase
      surveyPerInstanceLink.surveyPerInstanceRef == 1L // Se asigna el id de la clase asignada
  }

  def "Crear una instancia de survey para una clase que no implementa la interfaz Surveyable"() {
    given:"Un usuario previamente creado y un Survey existente"
      def anotherUserTest = new AnotherUserTest().save()
      new Survey().save(validate:false)
    when:"Asignamos una instancia de Survey a un usuario"
      def surveyPerInstanceLink = service.createSurveyPerInstance(anotherUserTest,1L)
    then:
      Exception e = thrown()
      e.message == "Pelaz!"
  }

  def "Obtener el surveyPerInstanceLink de una entidad, mediante el codeName del survey"() {
    given:"Dada una entidad con varios surveyPerInstance"
     def userTest = new UserTest().save(validate:false)
     createManySurveyPerInstance(userTest)
     def codeNameABucar="groovy-initial"
    when:"Buscar en la entidad el survey con el codeName groovy-initial"
      def surveyPerInstanceLink = service.findSurveyPerInstanceByCodeName(userTest.id,codeNameABucar)
    then:
      surveyPerInstanceLink.surveyPerInstance!=null
      surveyPerInstanceLink.surveyPerInstance.survey.codeName==codeNameABucar 
  }


  private def createManySurveyPerInstance(def instance){
    new Survey(title:"groovy",codeName:"groovy-initial").save(validate:false)
    new Survey(title:"grails",codeName:"groovy").save(validate:false)
    new Survey(title:"java",codeName:"grails-initial").save(validate:false)
    def surveyPerInstanceLink1 = service.createSurveyPerInstance(instance,1L)
    def surveyPerInstanceLink2 = service.createSurveyPerInstance(instance,2L)
    def surveyPerInstanceLink3 = service.createSurveyPerInstance(instance,3L)
  }

}
