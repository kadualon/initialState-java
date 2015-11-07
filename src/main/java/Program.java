import com.alonkadury.initialState.*;

public class Program {
  public static void main(String[] args) {
    Account account = new Account("ACCOUNT_KEY");
    Bucket bucket = new Bucket("myb1234", "easy name");
    account.createBucket(bucket);
    BooleanData data = new BooleanData("mykey", true);
    account.createData(bucket, data);
    account.createBulkData(bucket, new Data[]{data, data});
  }
}
