package org.msvdev.ee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;


@WebServlet(urlPatterns = "/products")
public class ProductListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductRepository productRepository = new ProductRepository();

        productRepository.insert(new Product("Телевизор", BigDecimal.valueOf(10000)));
        productRepository.insert(new Product("Фотоаппарат", BigDecimal.valueOf(1000)));
        productRepository.insert(new Product("Тостер", BigDecimal.valueOf(2500)));
        productRepository.insert(new Product("Микроволновая печь", BigDecimal.valueOf(6000)));
        productRepository.insert(new Product("Телефон", BigDecimal.valueOf(15000)));
        productRepository.insert(new Product("Холодильник", BigDecimal.valueOf(45000)));
        productRepository.insert(new Product("Стиральная машина", BigDecimal.valueOf(38000)));
        productRepository.insert(new Product("Хлебопечка", BigDecimal.valueOf(12000)));
        productRepository.insert(new Product("Индукционная плита", BigDecimal.valueOf(3600)));
        productRepository.insert(new Product("Тостер", BigDecimal.valueOf(1500)));



        PrintWriter writer = resp.getWriter();

        writer.println("<table>");

        // Заголовок таблицы
        writer.print("<tr>");
        writer.print("<th>ID</th>");
        writer.print("<th>Название продукта</th>");
        writer.print("<th>стоимость</th>");
        writer.print("</tr>");

        // Тело таблицы
        for (Product product: productRepository.findAll()) {
            writer.print("<tr>");
            writer.printf("<td>%d</td>", product.getId());
            writer.printf("<td>%s</td>", product.getTitle());
            writer.printf("<td>%.2f</td>", product.getCost().floatValue());
            writer.print("</tr>");
        }

        writer.println("</table>");
    }

}
