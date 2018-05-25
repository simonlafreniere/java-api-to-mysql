
public class News {
	 private String title, description, url ;
	 private int publishedAt;
	 
	 public News(String title,String description, String url, int publishedAt) {
		 this.title=title;
		 this.description=description;
		 this.url=url;
		 this.publishedAt=publishedAt;
	 }
	 
	 public void setTitle(String title) {
		 this.title=title;
	 }
	 
	 public void setDescription(String description) {
		 this.description=description;
	 }
	 
	 public void setUrl(String url) {
		 this.url=url;
		 	 }
	 
	 public void setPublishedAt(int publishedAt) {
		 this.publishedAt=publishedAt;
	 }
	 
	 public String getTitle() {
		 return title;
	 }
	 
	 public String getDescription() {
		 return description;
	 }
	 
	 public String getUrl() {
		 return url;
	 }
	 
	 public int getPublishedAt() {
		 return publishedAt;
	 }
	 
	 @Override
	 public String toString() {
	     return "Product [title=" + title+ ", description= " + description+",url= "+url+", publishedAt= "+publishedAt+"]";
	 }
}
