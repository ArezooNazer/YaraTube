package com.example.daryacomputer.yaratube.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.example.daryacomputer.yaratube.data.source.Constant.BASE_URL;

public class Product implements Parcelable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_english")
    @Expose
    private String nameEnglish;
    @SerializedName("product_type")
    @Expose
    private Integer productType;
    @SerializedName("producer")
    @Expose
    private Producer producer;
    @SerializedName("producer_name")
    @Expose
    private String producerName;
    @SerializedName("payment_type")
    @Expose
    private List<Object> paymentType = null;
    @SerializedName("category")
    @Expose
    private List<Integer> category = null;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;
    @SerializedName("feature_avatar")
    @Expose
    private FeatureAvatar featureAvatar;
    @SerializedName("rank")
    @Expose
    private Float rank;
    @SerializedName("totalInstalled")
    @Expose
    private Integer totalInstalled;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("promotionalContainers")
    @Expose
    private List<Object> promotionalContainers = null;
    @SerializedName("is_purchased")
    @Expose
    private Boolean isPurchased;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("files")
    @Expose
    private List<File> files = null;
    @SerializedName("generic_files")
    @Expose
    private List<Object> genericFiles = null;
    @SerializedName("director")
    @Expose
    private List<Object> director = null;
    @SerializedName("movie_producer")
    @Expose
    private List<Object> movieProducer = null;
    @SerializedName("cast")
    @Expose
    private List<Object> cast = null;
    @SerializedName("date_create")
    @Expose
    private Object dateCreate;
    @SerializedName("is_jalali")
    @Expose
    private Boolean isJalali;
    @SerializedName("is_bookmarked")
    @Expose
    private Boolean isBookmarked;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("category_model")
    @Expose
    private List<CategoryModel> categoryModel = null;
    @SerializedName("comments_summery")
    @Expose
    private List<CommentsSummery> commentsSummery = null;
    @SerializedName("price_unit")
    @Expose
    private String priceUnit;
    @SerializedName("total_view")
    @Expose
    private Integer totalView;
    @SerializedName("is_enable")
    @Expose
    private Boolean isEnable;
    @SerializedName("custom_json")
    @Expose
    private Object customJson;
    @SerializedName("polls")
    @Expose
    private List<Object> polls = null;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("invest_goal")
    @Expose
    private Object investGoal;
    @SerializedName("product_staff")
    @Expose
    private List<Object> productStaff = null;
    @SerializedName("support")
    @Expose
    private Support support;
    @SerializedName("is_special")
    @Expose
    private Boolean isSpecial;
    @SerializedName("additional_attributes")
    @Expose
    private List<Object> additionalAttributes = null;
    @SerializedName("date_published")
    @Expose
    private String datePublished;
    @SerializedName("customjson")
    @Expose
    private Object customjson;
    @SerializedName("last_checked_file")
    @Expose
    private Object lastCheckedFile;

    protected Product(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        nameEnglish = in.readString();
        if (in.readByte() == 0) {
            productType = null;
        } else {
            productType = in.readInt();
        }
        producerName = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
        if (in.readByte() == 0) {
            rank = null;
        } else {
            rank = in.readFloat();
        }
        if (in.readByte() == 0) {
            totalInstalled = null;
        } else {
            totalInstalled = in.readInt();
        }
        shortDescription = in.readString();
        description = in.readString();
        byte tmpIsPurchased = in.readByte();
        isPurchased = tmpIsPurchased == 0 ? null : tmpIsPurchased == 1;
        if (in.readByte() == 0) {
            comments = null;
        } else {
            comments = in.readInt();
        }
        byte tmpIsJalali = in.readByte();
        isJalali = tmpIsJalali == 0 ? null : tmpIsJalali == 1;
        byte tmpIsBookmarked = in.readByte();
        isBookmarked = tmpIsBookmarked == 0 ? null : tmpIsBookmarked == 1;
        sku = in.readString();
        tags = in.createStringArrayList();
        priceUnit = in.readString();
        if (in.readByte() == 0) {
            totalView = null;
        } else {
            totalView = in.readInt();
        }
        byte tmpIsEnable = in.readByte();
        isEnable = tmpIsEnable == 0 ? null : tmpIsEnable == 1;
        dateAdded = in.readString();
        byte tmpIsSpecial = in.readByte();
        isSpecial = tmpIsSpecial == 0 ? null : tmpIsSpecial == 1;
        datePublished = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public List<Object> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(List<Object> paymentType) {
        this.paymentType = paymentType;
    }

    public List<Integer> getCategory() {
        return category;
    }

    public void setCategory(List<Integer> category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getAvatarUrl(){
        return BASE_URL + getAvatar().getHdpi();
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public FeatureAvatar getFeatureAvatar() {
        return featureAvatar;
    }

    public void setFeatureAvatar(FeatureAvatar featureAvatar) {
        this.featureAvatar = featureAvatar;
    }

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }

    public Integer getTotalInstalled() {
        return totalInstalled;
    }

    public void setTotalInstalled(Integer totalInstalled) {
        this.totalInstalled = totalInstalled;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getPromotionalContainers() {
        return promotionalContainers;
    }

    public void setPromotionalContainers(List<Object> promotionalContainers) {
        this.promotionalContainers = promotionalContainers;
    }

    public Boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(Boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Object> getGenericFiles() {
        return genericFiles;
    }

    public void setGenericFiles(List<Object> genericFiles) {
        this.genericFiles = genericFiles;
    }

    public List<Object> getDirector() {
        return director;
    }

    public void setDirector(List<Object> director) {
        this.director = director;
    }

    public List<Object> getMovieProducer() {
        return movieProducer;
    }

    public void setMovieProducer(List<Object> movieProducer) {
        this.movieProducer = movieProducer;
    }

    public List<Object> getCast() {
        return cast;
    }

    public void setCast(List<Object> cast) {
        this.cast = cast;
    }

    public Object getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Object dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Boolean getIsJalali() {
        return isJalali;
    }

    public void setIsJalali(Boolean isJalali) {
        this.isJalali = isJalali;
    }

    public Boolean getIsBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(Boolean isBookmarked) {
        this.isBookmarked = isBookmarked;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<CategoryModel> getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(List<CategoryModel> categoryModel) {
        this.categoryModel = categoryModel;
    }

    public List<CommentsSummery> getCommentsSummery() {
        return commentsSummery;
    }

    public void setCommentsSummery(List<CommentsSummery> commentsSummery) {
        this.commentsSummery = commentsSummery;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getTotalView() {
        return totalView;
    }

    public void setTotalView(Integer totalView) {
        this.totalView = totalView;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Object getCustomJson() {
        return customJson;
    }

    public void setCustomJson(Object customJson) {
        this.customJson = customJson;
    }

    public List<Object> getPolls() {
        return polls;
    }

    public void setPolls(List<Object> polls) {
        this.polls = polls;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Object getInvestGoal() {
        return investGoal;
    }

    public void setInvestGoal(Object investGoal) {
        this.investGoal = investGoal;
    }

    public List<Object> getProductStaff() {
        return productStaff;
    }

    public void setProductStaff(List<Object> productStaff) {
        this.productStaff = productStaff;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public Boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public List<Object> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public void setAdditionalAttributes(List<Object> additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public Object getCustomjson() {
        return customjson;
    }

    public void setCustomjson(Object customjson) {
        this.customjson = customjson;
    }

    public Object getLastCheckedFile() {
        return lastCheckedFile;
    }

    public void setLastCheckedFile(Object lastCheckedFile) {
        this.lastCheckedFile = lastCheckedFile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        parcel.writeString(nameEnglish);
        if (productType == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(productType);
        }
        parcel.writeString(producerName);
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(price);
        }
        if (rank == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(rank);
        }
        if (totalInstalled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalInstalled);
        }
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeByte((byte) (isPurchased == null ? 0 : isPurchased ? 1 : 2));
        if (comments == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(comments);
        }
        parcel.writeByte((byte) (isJalali == null ? 0 : isJalali ? 1 : 2));
        parcel.writeByte((byte) (isBookmarked == null ? 0 : isBookmarked ? 1 : 2));
        parcel.writeString(sku);
        parcel.writeStringList(tags);
        parcel.writeString(priceUnit);
        if (totalView == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalView);
        }
        parcel.writeByte((byte) (isEnable == null ? 0 : isEnable ? 1 : 2));
        parcel.writeString(dateAdded);
        parcel.writeByte((byte) (isSpecial == null ? 0 : isSpecial ? 1 : 2));
        parcel.writeString(datePublished);
    }
}