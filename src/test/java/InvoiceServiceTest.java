import com.invoice.generator.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceService invoiceService = null;

    @Before
    public void setUp() {
        invoiceService = new InvoiceService();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(RidesCategories.NORMAL_RIDE, distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(RidesCategories.NORMAL_RIDE, distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RidesCategories.PREMIUM_RIDE, 2.0, 5),
                new Ride(RidesCategories.NORMAL_RIDE, 0.1, 1)
        };
        InvoiceSummary summery = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummary, summery);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId = UserIDList.getUserList();
        Ride[] rides = {new Ride(RidesCategories.PREMIUM_RIDE, 2.0, 5),
                new Ride(RidesCategories.PREMIUM_RIDE, 0.1, 1)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summery = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summery);
    }
}
