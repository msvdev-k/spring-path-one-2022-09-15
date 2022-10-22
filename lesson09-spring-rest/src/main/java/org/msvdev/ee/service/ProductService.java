package org.msvdev.ee.service;

import org.msvdev.ee.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Optional;


public interface ProductService {

    Optional<ProductDto> findById(Long id);


    /**
     * Поиск списка товаров согласно параметрам фильтра.
     * Если параметру присвоено значение null, то фильтрация
     * по этому параметру не производится.
     * Параметры page и pageSize являются обязательными.
     * Параметры sortField и sortOrder работают в паре.
     *
     * @param page номер страницы с описанием товаров (нумерация страниц начинается с нуля)
     * @param pageSize количество товаров, отображаемых на одной странице
     * @param title название товара
     * @param minCost минимальная стоимость товара
     * @param maxCost максимальная стоимость товара
     * @param sortField название поля по которому производится сортировка товаров
     * @param sortOrder направление сортировки. Допустимые значения: "ASC" и "DESC"
     * @return страница с описанием товаров
     */
    Page<ProductDto> findWithFilter(Integer page, Integer pageSize,
                                    String title,
                                    BigDecimal minCost,
                                    BigDecimal maxCost,
                                    String sortField,
                                    String sortOrder);

    ProductDto save(ProductDto product);

    void deleteById(Long id);
}