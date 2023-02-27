package carsharing.Dao;

import java.util.List;

public interface CompanyDao {
    List<Company> getAllCompanies();
    int createCompany(Company company);
    void createTable();
    Company selectCompany(int id);
    int findIdCompanyByName(String name);
}
