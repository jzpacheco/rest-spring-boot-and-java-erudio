package br.com.jzpacheco.restspringbootandjavaerudio;

import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/sum/{numberOne}/{numberTwo}",
                    method= RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception{
        if(!isNumeric(numberOne) ||!isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }
    @RequestMapping(value="/sub/{numberOne}/{numberTwo}",
                    method= RequestMethod.GET)
    public Double sub(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception{
        if(!isNumeric(numberOne) ||!isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }
    @RequestMapping(value="/mult/{numberOne}/{numberTwo}",
                    method= RequestMethod.GET)
    public Double mult(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception{
        if(!isNumeric(numberOne) ||!isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }
    @RequestMapping(value="/div/{numberOne}/{numberTwo}",
                    method= RequestMethod.GET)
    public Double div(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception{
        if(!isNumeric(numberOne) ||!isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        if(isNumeric(numberOne) && Double.parseDouble(numberOne) == 0){
           throw new UnsupportedMathOperationException("Please set a dividend greater than zero!");
        }

        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    private Double convertToDouble(String strNumber) throws Exception {
        if(strNumber == null) {
            throw new Exception();
        }
        String number = strNumber.replaceAll(",",".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",",".");
        return number.matches("[+-]?[0-9]*\\.?[0-9]+");
    }
}
