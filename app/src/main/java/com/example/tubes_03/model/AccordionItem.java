package com.example.tubes_03.model;

import android.content.ClipData;

import com.sysdata.widget.accordion.Item;

import java.util.Objects;

public class AccordionItem extends Item {

    private String mTitle;
    private String mDescription;

    public static AccordionItem create(String title, String description) {
        return new AccordionItem(title, description);
    }

    AccordionItem(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    @Override
    public int getUniqueId() {
        return hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AccordionItem that = (AccordionItem) o;

        if (!mTitle.equals(that.mTitle))
            return false;
        return Objects.equals(mDescription, that.mDescription);
    }

    @Override
    public int hashCode() {
        int result = mTitle.hashCode();
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        return result;
    }
}
