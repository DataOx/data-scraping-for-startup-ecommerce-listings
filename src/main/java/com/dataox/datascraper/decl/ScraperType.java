package com.dataox.datascraper.decl;

public enum ScraperType {
    BHPHOTOVIDEO("bhphotovideo"),
    ABELCINE("abelcine"),
    USA_CANON("usa.canon"),
    ADORAMA("adorama"),
    ELECTRONICS_SONY("electronics.sony"),
    ARRI_COM("arri.com"),
    UNKNOWN(null);

    private final String addressPart;

    public String getAddressPart() {
        return addressPart;
    }

    ScraperType(String addressPart) {
        this.addressPart = addressPart;
    }
}
