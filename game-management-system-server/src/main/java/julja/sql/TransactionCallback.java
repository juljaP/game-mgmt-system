package julja.sql;

public interface TransactionCallback {

  Object doInTransaction() throws Exception;

}
