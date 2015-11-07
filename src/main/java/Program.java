import com.alonkadury.initialState.*;

public class Program {
  public static void main(String[] args) {

    // create API - on current thread
    API account = new API("MyAccessKey");

    // create API that will use threadpool of 5 threads to send messages
    API threadedAccount = new API("MyAccessKey", 5);

    // create bucket
    Bucket bucket = new Bucket("myBucket", "Test bucket");
    account.createBucket(bucket);

    // create data
    Data data = new Data("Tempreture", 37);
    account.createData(bucket, data);

    // create bulk data
    account.createBulkData(bucket, new Data[]{data, data});

    // close
    threadedAccount.terminate();
  }
}
