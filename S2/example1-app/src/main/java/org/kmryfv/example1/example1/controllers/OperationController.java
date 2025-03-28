package org.kmryfv.example1.example1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class OperationController {
    @GetMapping("/hello")
    public Map<String, String> helloWorld(){
        Map<String, String> json = new HashMap<>();
        json.put("mensaje", "hola mundo");
        return json;
    }

    @PostMapping("/add")
    public Map<String, Integer> add (@RequestBody Map<String, Integer> request) {
        Map<String, Integer> result = new HashMap<>();
        int num1 = request.get("num1");
        int num2 = request.get("num2");
        result.put("sum", num1 + num2);
        return result;
    }

    @PostMapping("/get_priceIVA")
    public Map<String, Double> calculatePriceWithIVA(@RequestBody Map<String, Double> request) {
        Map<String, Double> result = new LinkedHashMap<>();
        double qty = request.get("quantity");
        double price = request.get("price");
        double subtotal = qty * price;
        double IVA = subtotal * 0.15;
        result.put("Subtotal", subtotal);
        result.put("IVA", IVA);
        result.put("Total", subtotal + IVA);
        return result;
    }

}
