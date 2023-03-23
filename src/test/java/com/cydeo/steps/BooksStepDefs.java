package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.DashBoardPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.util.List;
import java.util.Map;

public class BooksStepDefs {
    BookPage bookPage=new BookPage();
    List<String> actualCategoryList;


    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);

    }


    @When("the user gets all book categories in webpage")
    public void the_user_gets_all_book_categories_in_webpage() {
        //potential wait here if tests fail
        actualCategoryList=BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);
        System.out.println("actualCategoryList = " + actualCategoryList);
    }

    @Then("user should be able to see following categories")
    public void user_should_be_able_to_see_following_categories(List<String> expectedCategoryList) {
        Assert.assertEquals(expectedCategoryList, actualCategoryList);
    }


    @When("I open book {string}")
    public void i_open_book(String bookName) {

        System.out.println("bookName = " + bookName);
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }

    @Then("verify book categories must match book categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
// rub query to get data from db
        DB_Util.runQuery("select name from book_categories");
//store the info inside the list
        List<String> expectedBookCategories = DB_Util.getColumnDataAsList("name");
        System.out.println("expectedBookCategories = " + expectedBookCategories);//

        //compare UI vs DB
        Assert.assertEquals(actualCategoryList,expectedBookCategories);
    }

}
