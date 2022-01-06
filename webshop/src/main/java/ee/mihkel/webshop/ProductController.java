package ee.mihkel.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("get-products")
    public List<Product> getProducts() {
        System.out.println("Pandi võtmise päring käima");
        return productRepository.findAll();
    }

    @PostMapping("add-product")
    public String addProduct(@RequestBody Product product) {
        System.out.println("Pandi lisamise päring käima");
        productRepository.save(product);
        return "Lisasin toote: " + product.getName();
    }

    @DeleteMapping("delete-product/{id}")
    public String deleteProduct(@RequestParam Long id) {
        System.out.println("Pandi kustutamise päring käima");
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
