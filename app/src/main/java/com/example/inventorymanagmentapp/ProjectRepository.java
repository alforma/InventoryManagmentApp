package com.example.inventorymanagmentapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProjectRepository {

    private ProductDAO productDAO;
    private PartDAO partDAO;

    private LiveData<List<ProductEntity>> allProducts;
    private LiveData<List<PartEntity>> allParts;

////////////////////////////////////////////////////////////////////////////////
    public ProjectRepository(Application application) {
        ProjectDatabase database = ProjectDatabase.getDatabase(application);
        productDAO = database.productDAO();
        allProducts = productDAO.getAllProducts();

        partDAO = database.partDAO();
        allParts = partDAO.getAllParts();
    }
/////////////////////////////////////////////////////////////////////////////////////
    public void insert(ProductEntity productEntity) {
        new InsertProductsAsyncTask(productDAO).execute(productEntity);
    }

    public void update(ProductEntity productEntity) {
        new UpdateProductsAsyncTask(productDAO).execute(productEntity);
    }

    public void delete(ProductEntity productEntity) {
        new DeleteProductsAsyncTask(productDAO ).execute(productEntity);
    }
    public void deleteAllProducts() {
        new DeleteAllProductsAsyncTask(productDAO ).execute();
    }


    public LiveData<List<ProductEntity>> getAllProducts() {
        return allProducts;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class InsertProductsAsyncTask extends AsyncTask<ProductEntity, Void, Void> {
        private ProductDAO productDAO;

        private InsertProductsAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(ProductEntity... productEntities) {
            productDAO.insert(productEntities[0]);
            return null;
        }
    }

    private static class UpdateProductsAsyncTask extends AsyncTask<ProductEntity, Void, Void> {
        private ProductDAO productDAO;

        private UpdateProductsAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(ProductEntity... productEntities) {
            productDAO.update(productEntities[0]);
            return null;
        }
    }

    private static class DeleteProductsAsyncTask extends AsyncTask<ProductEntity, Void, Void> {
        private ProductDAO productDAO;

        private DeleteProductsAsyncTask(ProductDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(ProductEntity... productEntities) {
            productDAO.delete(productEntities[0]);
            return null;
        }
    }

    private static class DeleteAllProductsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProductDAO productDAO;

        private DeleteAllProductsAsyncTask(ProductDAO productDAO) {
            this.productDAO =productDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDAO.deleteAllProducts();
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////






    public void insert(PartEntity partEntity) {
        new InsertPartsAsyncTask(partDAO).execute(partEntity);
    }

    public void update(PartEntity partEntity) {
        new UpdatePartsAsyncTask(partDAO).execute(partEntity);
    }

    public void delete(PartEntity partEntity) {
        new DeletePartsAsyncTask(partDAO ).execute(partEntity);
    }
    public void deleteAllParts() {
        new DeleteAllPartsAsyncTask(partDAO ).execute();
    }


    public LiveData<List<PartEntity>> getAllParts() {
        return allParts;
    }

    /////////////////////////////////////////////////

    private static class InsertPartsAsyncTask extends AsyncTask<PartEntity, Void, Void> {
        private PartDAO partDAO;

        private InsertPartsAsyncTask(PartDAO partDAO) {
            this. partDAO =  partDAO;
        }

        @Override
        protected Void doInBackground(PartEntity... partEntities) {
            partDAO.insert( partEntities[0]);
            return null;
        }
    }

    private static class UpdatePartsAsyncTask extends AsyncTask<PartEntity, Void, Void> {
        private PartDAO partDAO;

        private UpdatePartsAsyncTask(PartDAO partDAO) {
            this. partDAO =  partDAO;
        }

        @Override
        protected Void doInBackground(PartEntity... partEntities) {
            partDAO.update(partEntities[0]);
            return null;
        }
    }

    private static class DeletePartsAsyncTask extends AsyncTask<PartEntity, Void, Void> {
        private PartDAO partDAO;

        private DeletePartsAsyncTask(PartDAO partDAO) {
            this. partDAO = partDAO;
        }

        @Override
        protected Void doInBackground(PartEntity...partEntities) {
            partDAO.delete(partEntities[0]);
            return null;
        }
    }

    private static class DeleteAllPartsAsyncTask extends AsyncTask<Void, Void, Void> {
        private PartDAO partDAO;

        private DeleteAllPartsAsyncTask(PartDAO partDAO) {
            this.partDAO =partDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            partDAO.deleteAllParts();
            return null;
        }
    }





}


