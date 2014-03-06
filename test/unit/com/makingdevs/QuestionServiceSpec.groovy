package com.makingdevs

import com.makingdevs.*
import static org.junit.Assert.*
import org.junit.*
import spock.lang.*


@TestFor(QuestionService)
@Mock([Question])
class QuestionServiceSpec extends Specification{

  @Unroll
  def "Crear una pregunta de tipo 'CON_CALIFICACION' "(){
    given:"Dado cuatro parametros" 
      def params=[description:"description",questionType:QuestionType.CON_CALIFICACION,from:"1",to:"3"]
    when:"Se llama el servicio para crear pregunta de tipo 'CON_CALIFICACION'"
      def pregunta = service.crearPreguntaConCalificacion(params)
    then:"Pregunta es de tipo 'CON_CALIFICACION' y con id mayor a cero"
      pregunta.id > 0
      pregunta.questionType == QuestionType.CON_CALIFICACION
      pregunta.satisfaccion==[1,2,3]
  }

  @Unroll
  def "Crear una pregunta de tipo 'SIN_CALIFICACION'"(){
    given:"Dado dos parametros" 
      def params=[description:"description",questionType:QuestionType.SIN_CALIFICACION]
    when:"Se llama el servicio para crear pregunta de tipo 'SIN_CALIFICACION'"
      def pregunta = service.crearPreguntaGenerica(params)
    then:"Pregunta es de tipo 'SIN_CALIFICACION' y con id mayor a cero"
      pregunta.id > 0
      pregunta.questionType == QuestionType.SIN_CALIFICACION
  }

  @Unroll
  def "Crear una pregunta de tipo 'ABIERTA'"(){
    given:"Dado dos parametros" 
      def params=[description:"description",questionType:QuestionType.ABIERTA]
    when:"Se llama el servicio para crear pregunta de tipo 'ABIERTA'"
      def pregunta = service.crearPreguntaGenerica(params)
    then:"Pregunta es de tipo 'ABIERTA' y con id mayor a cero"
      pregunta.id > 0
      pregunta.questionType == QuestionType.ABIERTA
  }


}