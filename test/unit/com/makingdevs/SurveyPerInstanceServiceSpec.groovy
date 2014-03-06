package com.makingdevs


import static org.junit.Assert.*
import org.junit.*
import spock.lang.*
import com.makingdevs.*


@TestFor(SurveyPerInstanceService)
@Mock([Survey,Question,Answer,AnswerPerInstance,SurveyPerInstance,AnswerPerUser,RangeAnswerPerUser,OpenAnswerPerUser])
class SurveyPerInstanceServiceSpec extends Specification{

  @Unroll
  def "Crear una instancia de un survey ya existente"(){
    given:"Dado un cuestionario" 
     def survey = createSurvey()
    when:"Se llama el servicio para crear una intancia y lleno con preguntas"
      def surveyPerInstance= service.instanceSurvey(survey.id)
    then:
      surveyPerInstance.id > 0
      surveyPerInstance.answerPerInstances.size() == 3
  }

  @Unroll
  def "Dada una instancia de survey agregar una respuesta 'con calificacion' dada por ele usuario"(){
    given:"Dada un survey y una intancia de survey ya lleno" 
      def survey = createSurvey()
      def surveyPerInstance = service.instanceSurvey(survey.id)
      def preguntaConCalificaionId = survey.questions.first().id
      def respuesta = Answer.get(1)
      respuesta=respuesta.id
      def satisfaccion = 2
      def observacion = "observacion"
    when:"Se llama el servicio para agrega la respuesta"
      service.addAnswer(preguntaConCalificaionId,respuesta,satisfaccion,observacion,survey.id)
    then:
      surveyPerInstance.answerPerInstances.getAt(0).answerPerUsers.size()==1
      surveyPerInstance.answerPerInstances.getAt(0).openAnswerPerUsers.size()==1
      surveyPerInstance.answerPerInstances.getAt(0).rangeAnswerPerUsers.size()==1
  }

  @Unroll
  def "Dada una instancia de survey agregar una respuesta 'sin calificacion' dada por ele usuario"(){
    given:"Dada un survey y una intancia de survey ya lleno" 
      def survey = createSurvey()
      def surveyPerInstance = service.instanceSurvey(survey.id)
      def preguntaSinCalificaionId = survey.questions.getAt(1).id
      def respuesta = Answer.get(3)
      respuesta=respuesta.id
      def satisfaccion
      def observacion = "observacion"
    when:"Se llama el servicio para agrega la respuesta"
      service.addAnswer(preguntaSinCalificaionId,respuesta,satisfaccion,observacion,survey.id)
    then:
      surveyPerInstance.answerPerInstances.getAt(1).answerPerUsers.size()==1
      surveyPerInstance.answerPerInstances.getAt(1).openAnswerPerUsers.size()==1
      surveyPerInstance.answerPerInstances.getAt(1).rangeAnswerPerUsers==null
  }

  @Unroll
  def "Dada una instancia de survey agregar una respuesta 'abierta' dada por ele usuario"(){
    given:"Dada un survey y una intancia de survey ya lleno" 
      def survey = createSurvey()
      def surveyPerInstance = service.instanceSurvey(survey.id)
      def preguntaAbiertaId = survey.questions.getAt(2).id
      def respuesta = "string"
      def satisfaccion
      def observacion 
    when:"Se llama el servicio para agrega la respuesta"
      service.addAnswer(preguntaAbiertaId,respuesta,satisfaccion,observacion,survey.id)
    then:
      surveyPerInstance.answerPerInstances.getAt(2).answerPerUsers==null
      surveyPerInstance.answerPerInstances.getAt(2).openAnswerPerUsers.size()==1
      surveyPerInstance.answerPerInstances.getAt(2).rangeAnswerPerUsers==null
  }

  private Survey createSurvey(){
    def survey = new Survey()
    survey.title="cuestionario de prueba"
    survey.description="Este es la descripcion"
    survey.codeName="codeName"
    survey.addToQuestions(new Question(description:"Pregunta con calificacion",
      questionType:QuestionType.CON_CALIFICACION,
      satisfaccion:1..3).addToAnswers(new Answer(description:"si")).addToAnswers(new Answer(description:"no")))

    survey.addToQuestions(new Question(description:"Pregunta con sin calificacion",
      questionType:QuestionType.SIN_CALIFICACION).addToAnswers(new Answer(description:"si")).addToAnswers(new Answer(description:"no")))

    survey.addToQuestions(new Question(description:"Pregunta con abierta",
      questionType:QuestionType.ABIERTA))

    survey.save(flush:true)
    survey
  }

}
