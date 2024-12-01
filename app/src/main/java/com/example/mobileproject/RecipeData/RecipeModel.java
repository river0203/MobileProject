package com.example.mobileproject.RecipeData;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private List<String> ingredients = new ArrayList<>();
    private static String jsonRecipe;
    private static String strIngredientList;

    // 여러 액티비에서 동일한 인스턴스를 사용하기 위한 싱글톤 패턴
    private static RecipeModel instance;
    private RecipeModel() {}
    public static RecipeModel getInstance() {
        if (instance == null) {
            instance = new RecipeModel();
        }
        return instance;
    }
    // 여기까지가 싱글톤 패턴

    //  Plus_Plus_Page에서 재료이름을 받음
    public void plusIngredients(String ingredient) {
        this.ingredients.add(ingredient);
        strIngredientList = String.join(" ", ingredients);
        System.out.println(strIngredientList);
    }

    // 재료 리스트를 보냄 -> NextActivity
    public List<String> getIngredients() {
        return this.ingredients;
        // 하드코딩 ㅁㅁㅁ
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("ham");
//        strings.add("eggs");
//        strings.add("rice");
//        return strings;
    }

    // RecipeClient에 재료를 보냄
    public String getStrIngredientList() {
        return strIngredientList;
    }

    // JSON형식 데이터 리스트를 RecipeClient에서 가져오기
    public void setJsonRecipe(String recipe) {
        jsonRecipe = recipe;
        System.out.println(jsonRecipe);
    }

    // 리스트 변환이 두번 됨
    // JSON형식의 데이터 리스트를 Recommend_Menu_page에 전달
    public String getJsonRecipe() {
        //         하드코딩 용 테스트
        String jsonString = "{\n" +
                "    \"recipes\": [\n" +
                "        {\n" +
                "            \"recipe_name\": \"계란말이\",\n" +
                "            \"steps\": [\n" +
                "                \"계란을 풀어 그릇에 담는다.\",\n" +
                "                \"양파와 햄을 잘게 다져준다.\",\n" +
                "                \"팬에 기름을 두르고 양파와 햄을 볶는다.\",\n" +
                "                \"양파와 햄이 익으면 계란을 부어 주기적으로 저어가며 익힌다.\",\n" +
                "                \"계란이 익으면 불을 끄고 그릇에 담아 완성한다.\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"recipe_name\": \"양파 닭가슴살 볶음\",\n" +
                "            \"steps\": [\n" +
                "                \"양파와 닭가슴살을 적당한 크기로 잘라준다.\",\n" +
                "                \"팬에 기름을 두르고 양파를 볶는다.\",\n" +
                "                \"양파가 익으면 닭가슴살을 넣고 함께 볶는다.\",\n" +
                "                \"양념장을 넣고 볶아 익힌다.\",\n" +
                "                \"완성된 요리를 그릇에 담아 완성한다.\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"recipe_name\": \"양파 계란볶음밥\",\n" +
                "            \"steps\": [\n" +
                "                \"양파와 닭가슴살을 적당한 크기로 잘라준다.\",\n" +
                "                \"팬에 기름을 두르고 양파를 볶는다.\",\n" +
                "                \"양파가 익으면 닭가슴살을 넣고 함께 볶는다.\",\n" +
                "                \"계란을 넣고 볶아 익힌다.\",\n" +
                "                \"밥을 넣고 볶아 완성한다.\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        String jsonRecipe = jsonString;
          return jsonRecipe;
    }

    // NextActivity에서 리스트를 삭제할 때 사용
    public void removeIngredients(List<String> ingredientsToRemove) {
        this.ingredients.removeAll(ingredientsToRemove);
    }
}
