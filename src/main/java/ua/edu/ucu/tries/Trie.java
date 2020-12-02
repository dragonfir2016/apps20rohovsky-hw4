package ua.edu.ucu.tries;

public interface Trie {
    // Додає в Trie кортеж - Tuple: слово - term, і його вагу - weight.
    // У якості ваги, використовуйте довжину слова
    public void add(Tuple word);

    // Чи є слово в Trie
    public boolean contains(String word);

    // Видаляє слово з Trie
    public boolean delete(String word);

    // Ітератор по всім словам (обхід дерева в ширину)
    public Iterable<String> words();

    // Ітератор по всім словам, які починаються з pref
    public Iterable<String> wordsWithPrefix(String pref);

    // Кількість слів в Trie
    public int size();
}
