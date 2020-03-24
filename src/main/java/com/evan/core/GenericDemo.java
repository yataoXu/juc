//
//// 查询可以返回Cursor<T>类型的数据，类似于JDBC里的ResultSet类，
//// 当查询百万级的数据的时候，使用游标可以节省内存的消耗，不需要一次性取出所有数据，可以进行逐条处理或逐条取出部分批量处理。
//public interface Cursor<T> extends Closeable, Iterable<T> {
//
//    boolean isOpen();
//
//    boolean isConsumed();
//
//    int getCurrentIndex();
//}