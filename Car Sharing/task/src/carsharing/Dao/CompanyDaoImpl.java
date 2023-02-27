package carsharing.Dao;

import carsharing.service.JDBCUtilityCompany;

import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    JDBCUtilityCompany utility;

    public CompanyDaoImpl(JDBCUtilityCompany utility) {
        this.utility = utility;
    }

    @Override
    public List<Company> getAllCompanies() {
        return utility.selectAllCompanies();
    }

    @Override
    public int createCompany(Company company) {
        return utility.createCompany(company);
    }

    @Override
    public void createTable() {
        utility.createTable();
    }

    @Override
    public Company selectCompany(int id) {
        return utility.selectCompanyById(id);
    }

    @Override
    public int findIdCompanyByName(String name) {
        return utility.findIdCompanyByName(name);
    }
}
