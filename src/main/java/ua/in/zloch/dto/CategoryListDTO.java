package ua.in.zloch.dto;

import java.util.*;

public class CategoryListDTO {
    private List<CategoryDTO> categories = new ArrayList<>();

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void addCategory(CategoryDTO category) {
        this.categories.add(category);
    }

    public class CategoryDTO {
        public static final String ID = "id";
        public static final String TITLE = "title";

        private Map<String, Object> category;

        public CategoryDTO() {
            category = new HashMap<>();
        }

        public void setId(long id) {
            this.category.put(ID, id);
        }

        public void setTitle(String title) {
            this.category.put(TITLE, title);
        }

        public Map<String, Object> getCategory() {
            return category;
        }
    }
}