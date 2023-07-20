/**
 * © Copyright SanSys Pvt. Ltd. All rights reserved. SanSys is a registered trademark and the SanSys
 * graphic logo is a trademark of SanSys Pvt. Ltd. SanSys reserves all the right for this source
 * code. You should not modify or reuse without the noticing it to SanSys. And need to provide
 * credits where applicable. Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific governing permissions and limitations under the License.
 *
 * @author - Sanjeev
 * @version - 1.0
 * @CreatedOn - 17-Jul-2023 11:59:27 pm
 * @Usage -
 *
 */

package com.sansys.loginservicesb3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring security configuration
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
 
  @Bean
  public InMemoryUserDetailsManager user() {
    UserDetails user = User.withUsername("user").password("{noop}user").roles("USER").build();
    UserDetails admin = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
    return new InMemoryUserDetailsManager(user, admin);

  }
   
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(CsrfConfigurer::disable);
    httpSecurity.cors(CorsConfigurer::disable);
    httpSecurity.authorizeHttpRequests(auth -> {
      auth.requestMatchers("/").permitAll();
      auth.requestMatchers("/home").hasAnyRole("USER");
      auth.requestMatchers("/admin/**").hasAnyRole("ADMIN");
      auth.anyRequest().authenticated();
    });
//    httpSecurity.formLogin(Customizer.withDefaults());
    httpSecurity.httpBasic(Customizer.withDefaults());
    httpSecurity.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    httpSecurity.headers(headers -> headers.frameOptions(fo -> fo.disable()));
    return httpSecurity.build();
  }
 }
