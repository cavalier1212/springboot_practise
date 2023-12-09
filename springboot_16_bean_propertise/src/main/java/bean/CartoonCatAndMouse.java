package bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import lombok.Data;

// @Component 不加，不強制申明，改為被 App @Import
@Data
// @ConditionalOnClass(Clazz.class) // 條件符合才加載以下properties
@EnableConfigurationProperties(CartoonProperties.class) // 讀取並加載成Bean，被加載的不用申明@Bean or @Component
public class CartoonCatAndMouse {
    private Cat cat;
    private Mouse mouse;
    
    private CartoonProperties cartoonProperties;

    public CartoonCatAndMouse(CartoonProperties cartoonProperties) {
        this.cartoonProperties = cartoonProperties;
        this.cat = cartoonProperties.getCat() != null ? cartoonProperties.getCat(): new Cat();
        this.mouse = cartoonProperties.getMouse() != null ? cartoonProperties.getMouse(): new Mouse();
        this.cat.setAge(cartoonProperties.getCat().getAge() != null ? cartoonProperties.getCat().getAge() : 2);
        this.cat.setName(cartoonProperties.getCat().getName() != null ? cartoonProperties.getCat().getName() : "Tom");
        this.mouse.setAge(cartoonProperties.getMouse().getAge() != null ? cartoonProperties.getMouse().getAge() : 1);
        this.mouse.setName(cartoonProperties.getMouse().getName() != null ? cartoonProperties.getMouse().getName() : "Jerry");
    }

    public void run(){
        System.out.println(this.cat.getAge()+"歲的"+this.cat.getName()+"正追著"+this.mouse.getAge()+"歲的"+this.mouse.getName());
    }
}
