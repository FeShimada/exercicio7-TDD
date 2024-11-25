import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class TestInvoice {
    
    static Invoice invoice;

    static List<Product> testData;

    static double discount;

    @BeforeAll
    static void setUpData() {
        invoice = new Invoice();

        testData = Arrays.asList( new Product("", 0, 0) );
        
        discount = 0;
    }

    // Produtos com valores válidos, desconto percentual aplicado, sem atingir R$ 1.000,00 antes do desconto.
    @Test
    public void testCt01() {
        testData = Arrays.asList(
            new Product("Produto 1", 100, 3), 
            new Product("Produto 2", 200, 2)
        );
        discount = 10;
        assertEquals(630, Invoice.calculateInvoice(testData, discount));
    }

    // Produtos com valores somados maior que 1000, desconto percentual aplicado com adicional de 100.
    @Test
    public void testCt02() {
        testData = Arrays.asList(
            new Product("Produto 1", 400, 2), 
            new Product("Produto 2", 300, 2)
        );
        discount = 15;
        assertEquals(1090, Invoice.calculateInvoice(testData, discount));
    }

    // 1 dos Produtos com valores negativos.
    @Test
    public void testCt03() {
        testData = Arrays.asList(
            new Product("Produto 1", -50, 1), 
            new Product("Produto 2", 300, 2)
        );
        discount = 10;
        assertThrows(InvalidProductException.class, () -> Invoice.calculateInvoice(testData, discount));
    }

    // 1 dos Produtos com quantidades negativas.
    @Test
    public void testCt04() {
        testData = Arrays.asList(
            new Product("Produto 1", 100, -1), 
            new Product("Produto 2", 300, 2)
        );
        discount = 10;
        assertThrows(InvalidProductException.class, () -> Invoice.calculateInvoice(testData, discount));
    }

    // Produtos com valores somados igual a 1000 não realizar desconto adicional de 100.
    @Test
    public void testCt05() {
        testData = Arrays.asList(
            new Product("Produto 1", 500, 1), 
            new Product("Produto 2", 500, 1)
        );
        discount = 10;
        assertEquals(900, Invoice.calculateInvoice(testData, discount));
    }
}
