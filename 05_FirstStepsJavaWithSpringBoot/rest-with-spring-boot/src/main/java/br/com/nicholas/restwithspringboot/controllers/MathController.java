package br.com.nicholas.restwithspringboot.controllers;

import br.com.nicholas.restwithspringboot.converter.NumberConverter;
import br.com.nicholas.restwithspringboot.exceptions.UnsupportedMathOperationException;
import br.com.nicholas.restwithspringboot.math.SimpleMath;
import org.springframework.web.bind.annotation.*;


@RestController
public class MathController {

    private SimpleMath simpleMath = new SimpleMath();

    //Path Variable example
    // por padrão é GET
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum (@PathVariable(value = "numberOne")  String numberOne,
                       @PathVariable(value = "numberTwo")  String numberTwo){
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.sum(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/subtract/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtract (@PathVariable(value = "numberOne")  String numberOne,
                       @PathVariable(value = "numberTwo")  String numberTwo){
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.subtract(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/divide/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double divide (@PathVariable(value = "numberOne")  String numberOne,
                       @PathVariable(value = "numberTwo")  String numberTwo){
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.divide(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiply (@PathVariable(value = "numberOne")  String numberOne,
                       @PathVariable(value = "numberTwo")  String numberTwo){
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.multiply(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean (@PathVariable(value = "numberOne")  String numberOne,
                          @PathVariable(value = "numberTwo")  String numberTwo){
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.mean(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/sqrRoot/{number}", method = RequestMethod.GET)
    public Double sqrRoot (@PathVariable(value = "number")  String number){
        if(!NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.sqrRoot(NumberConverter.convertToDouble(number));
    }
}
