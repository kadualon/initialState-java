import com.alonkadury.initialState.*;

public class Program {
  public static void main(String[] args) {
    Account account = new Account("ACCOUNT_KEY");
    Bucket bucket = new Bucket("myb1234", null);
    account.createBucket(bucket);
    Data data = new Data("mykey", true);
    account.createData(bucket, data);
    account.createBulkData(bucket, new Data[]{data, data});
  }
}
