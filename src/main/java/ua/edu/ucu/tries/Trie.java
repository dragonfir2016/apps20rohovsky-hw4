package ua.edu.ucu.tries;

public interface Trie {
    // Додає в Trie кортеж - Tuple: слово - term, і його вагу - weight.
    // У якості ваги, використовуйте довжину слова
    void add(Tuple word);

    // Чи є слово в Trie
    boolean contains(String word);

    // Видаляє слово з Trie
    boolean delete(String word);

    // Ітератор по всім словам (обхід дерева в ширину)
    Iterable<String> words();

    // Ітератор по всім словам, які починаються з pref
    Iterable<String> wordsWithPrefix(String pref);

    // Кількість слів в Trie
    int size();
}
