package by.training.parser.parser;

import by.training.parser.entity.Candy;
import by.training.parser.exception.ParserException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCandiesBuilder {
    protected Set<Candy> candies;

    public AbstractCandiesBuilder() {
        candies = new HashSet<Candy>();
    }

    public AbstractCandiesBuilder(Set<Candy> candies) {
        this.candies = candies;
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public abstract void buildSetCandies(String filePath) throws ParserException;
}
