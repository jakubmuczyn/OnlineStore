package database;

import products.Product;

import java.util.List;
import java.util.Optional;

public interface DaoInterface <T> {
    Optional<T> get(int id);

    List<T> getAll();
    void save(T t);
    void update(String parameterName, Object parameterValue);
    void delete(T t);
}
