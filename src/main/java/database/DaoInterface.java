package database;

import products.Product;

import java.util.List;
import java.util.Optional;

public interface DaoInterface <T> {
    Optional<T> get(int id);
    Optional<T> get(T item);

    List<T> getAll();
    void save(T t);
    // W samej metodzie po nazwie parametrów
    <K> void update(T product, String parameterName, K parameterValue);

    void delete(T t);
}
