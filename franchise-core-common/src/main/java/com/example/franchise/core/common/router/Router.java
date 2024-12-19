package com.example.franchise.core.common.router;

public class Router {

    private Router() {
        throw new IllegalStateException("Router");
    }
    private static final String API = "/api";

    public static class FranchiseAPI {
        private FranchiseAPI() {
            throw new IllegalStateException("FranchiseAPI");
        }

        public static final String ROOT = API + "/franchise";
        public static final String NAME = "/name";
    }

    public static class BranchAPI {
        private BranchAPI() {
            throw new IllegalStateException("BranchAPI");
        }

        public static final String ROOT = API + "/branch";
        public static final String NAME = "/name";
    }

    public static class ProductAPI {
        private ProductAPI() {
            throw new IllegalStateException("ProductAPI");
        }

        public static final String ROOT = API + "/product";
        public static final String TOP = "/top/{franchiseId}";
        public static final String NAME = "/name";
        public static final String STOCK = "/stock";
        public static final String ID = "/{id}";
    }

}
