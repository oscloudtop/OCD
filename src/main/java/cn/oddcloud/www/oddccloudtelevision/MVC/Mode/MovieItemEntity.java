package cn.oddcloud.www.oddccloudtelevision.MVC.Mode;

/**
 * Created by wangyujie on 2016/11/3.
 */
public class MovieItemEntity {
    private String Title;
    private int Img;

    public MovieItemEntity(int img, String title) {
        this.Img=img;
        this.Title=title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }
}
