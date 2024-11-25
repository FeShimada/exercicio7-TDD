import java.util.List;

// ● Se o valor total da fatura antes do desconto for maior que R$ 1.000,00, o cliente ganha um desconto adicional de R$ 100,00.
// ● Se algum produto tiver preço ou quantidade negativa, a função deve lançar uma exceção (InvalidProductException).
public class Invoice {
    double total;
    public Invoice(double total) {
        this.total = total;
    }
    public Invoice(){}

    public static double calculateInvoice(List<Product> products, double discount) {
        double total = 0.0;

        for (Product product : products) {
            // ● Se algum produto tiver preço ou quantidade negativa, a função deve lançar uma exceção (InvalidProductException).
            if (product.price < 0 || product.quantity < 0) {
                throw new InvalidProductException("Produto com preço ou quantidade inválida");
            }
            total += product.price * product.quantity;
        }

        double totalDiscount = total - (total * (discount / 100));

        // ● Se o valor total da fatura antes do desconto for maior que R$ 1.000,00, o cliente ganha um desconto adicional de R$ 100,00.
        if (total > 1000.0) {
            totalDiscount -= 100.0;
        }

        return totalDiscount;
    }
}
