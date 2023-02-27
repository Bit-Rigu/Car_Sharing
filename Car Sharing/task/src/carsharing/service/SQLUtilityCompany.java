package carsharing.service;

import carsharing.Dao.Company;

import java.util.List;

public interface SQLUtilityCompany {
    void createTable();
    List<Company> selectAllCompanies();
    int createCompany(Company company);
    Company selectCompanyById(int id);
    int findIdCompanyByName(String name);
}
