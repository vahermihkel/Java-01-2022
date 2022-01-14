package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.model.Product;
import ee.mihkel.webshop.exception.ProductNotFoundException;
import ee.mihkel.webshop.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller võimaldab frontendil päringuid teha
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    // programmeerija
    //         500-1000 bruto 1.l aastal
    //          3 aastat 3000-4000
    // IT tugi - logides uurija ja info andja kus viga võib olla
    // analüütik - suhtleb kliendiga ja ütleb palju iga arendus
    //          läheb maksma
    // testija - kui arendajad teevad koodi valmis, proovivad ise 1-2x
    //          korda läbi, kui töötab, siis annab testijatele
    //          kõikvõimalikud stsenaariumid läbi mängida, et
    //          viga ei tekiks kliendile (kõik brauserid läbi)
    //          kõik ajavööndid, kõik maksed
    //          taani keelseid tähti, poolas
    // disainerid - 100 inimest ja 3 disainerit
    // kliendi pool on tavaliselt süsteemiadministraatorid
    // 1500 bruto, tõuseb max 2500 bruto

    // otseühendus selle klassiga
    // serverite jaoks, et oleks koguaeg kättesaadav kui päring tuleb
    @Autowired
    ProductRepository productRepository;

    // peab olema GET päring aadressile http://localhost:8080/get-products
    @GetMapping("get-products")
    public List<Product> getProducts() {
        // logid - vigade leidmiseks
        System.out.println("Pandi võtmise päring käima");
        return productRepository.findAll();
    }

    // peab olema POST päring aadressile http://localhost:8080/add-product
    // ja peab kaasa andma body
    @PostMapping("add-product")
    @Operation(summary = "Toote lisamine veebipoodi")
    public String addProduct(@RequestBody Product product) {
        System.out.println("Pandi lisamise päring käima");
        productRepository.save(product);
        return "Lisasin toote: " + product.getName();
    }

    // peab olema DELETE päring aadressile http://localhost:8080/delete-product/2
                                // loogelise sulu vahel on päringu aadressi muutuja
    @DeleteMapping("delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        System.out.println("Pandi kustutamise päring käima");
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ProductNotFoundException();
        }
        return "Kustutasin toote ID-ga: " + id;
    }

}

// 4 17.30-20.30    17.30-20.00 05.01.2022
// 4 17.30-20.30    17.30-20.00 10.01.2022
// 4 17.30-20.30    17.30-20.00 12.01.2022
// 2 17.30-19.00    17.30-20.30 17.01.2022
// 70ak/h
// 70/4 = 17,5

// Model-View-Controller MVC
// Model - andmemudel: kasutaja, toode, tellimus
// View - frontend
// Controller - paneb käima päringuid ja nö seob Modeli ja View
