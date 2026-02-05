-- =============================================
-- 1. CLEAN UP (Xóa dữ liệu cũ để tránh trùng lặp)
-- =============================================
TRUNCATE TABLE products RESTART IDENTITY CASCADE;
TRUNCATE TABLE categories RESTART IDENTITY CASCADE;

-- =============================================
-- 2. INSERT CATEGORIES
-- =============================================
INSERT INTO categories (category_name) VALUES
                                           ('Smartphone'),
                                           ('Laptop'),
                                           ('Smartwatch'),
                                           ('Headphone'),
                                           ('Electronic Accessories');

-- =============================================
-- 3. INSERT PRODUCTS
-- =============================================

-- --- 1. SMARTPHONES ---
INSERT INTO products (product_name, price, description, image, stock_quantity, is_active, category_id)
SELECT v.name, v.price, v.descr, v.img, 50, TRUE, c.category_id
FROM categories c
         CROSS JOIN (VALUES
                         ('iPhone 15 Pro Max', 34990000, 'Titanium design, A17 Pro chip.', 'https://static1.pocketnowimages.com/wordpress/wp-content/uploads/2023/09/pbi-iphone-15-pro-max.png'),
                         ('Samsung Galaxy S24 Ultra', 33990000, 'Galaxy AI, 200MP camera.', 'https://image-us.samsung.com/us/smartphones/galaxy-s24/all-gallery/01_E3_OnlineExclusive_TitaniumBlue_Lockup_1600x1200.jpg'),
                         ('Google Pixel 8 Pro', 28990000, 'Google AI camera, pure Android.', 'https://s.isanook.com/hi/0/ud/317/1589987/pix8pro_3.jpg'),
                         ('Xiaomi 14 Ultra', 24990000, 'Leica professional optics.', 'https://i02.appmifile.com/334_operator_sg/22/02/2024/d36105f6de5a716a1c0737352c2827be.png'),
                         ('OnePlus 12', 19990000, 'Smooth beyond belief.', 'https://oasis.opstatics.com/content/dam/oasis/page/2023/cn/12/12-whtie.png'),
                         ('Sony Xperia 1 V', 29990000, 'Next-gen sensor for creators.', 'https://fdn.gsmarena.com/imgroot/news/23/05/sony-xperia-1-v-official/inline/-1200/gsmarena_004.jpg'),
                         ('Oppo Find X7 Ultra', 22990000, 'Quad main camera system.', 'https://image.oppo.com/content/dam/oppo/common/mkt/v2-2/find-x7-series-cn/specs/find-x7-ultra-976_720.png'),
                         ('Vivo X100 Pro', 21990000, 'Zeiss optics, Dimensity 9300.', 'https://static1.pocketlintimages.com/wordpress/wp-content/uploads/2023/12/vivo-x100-pro.jpg'),
                         ('iPhone 15', 22990000, 'Dynamic Island, 48MP main camera.','https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2023/09/muo-featured-image-fixed-size-15.jpg'),
                         ('Samsung Galaxy S24', 18990000, 'Compact design, powerful AI.', 'https://static1.anpoimages.com/wordpress/wp-content/uploads/2024/01/galaxy-s24-amberyellow-square.jpg'),
                         ('Asus ROG Phone 8', 24990000, 'Ultimate gaming phone.', 'https://media.assettype.com/bloombergquint/2024-01/ca6ac0e5-aa32-4f24-ae73-28f4a34fb025/WhatsApp_Image_2024_01_09_at_14_21_07.jpeg'),
                         ('Red Magic 9 Pro', 19990000, 'Flat back design, cooling fan.', 'https://www.mobilissimo.ro/imgs/uploaded/images/Nubia/red%20magic%209%20pro%207.jpg'),
                         ('Honor Magic 6 Pro', 21990000, 'Falcon camera system.', 'https://basic-tutorials.de/wp-content/uploads/2024/03/HONOR_Magic6-Pro_Front.jpg'),
                         ('Huawei Pura 70 Ultra', 31990000, 'Retractable camera lens.', 'https://m.media-amazon.com/images/I/71bIlNmn9gL.jpg'),
                         ('Nothing Phone (2)', 15990000, 'Iconic Glyph interface.', 'https://www.digitaltrends.com/wp-content/uploads/2023/07/nothing-phone-2-home-widgets.jpg'),
                         ('Realme GT 5 Pro', 14990000, 'Flagship killer performance.', 'https://techmaniacs.gr/wp-content/uploads/2023/10/realme-gt-5-pro.jpg'),
                         ('Poco F6 Pro', 12990000, 'HyperPower evolved.', 'https://m.media-amazon.com/images/I/61gDoLAo7qL.jpg'),
                         ('Samsung Galaxy Z Fold 5', 40990000, 'PC-like power in your pocket.', 'https://m.media-amazon.com/images/I/61A1RYHuSHL.jpg'),
                         ('Samsung Galaxy Z Flip 5', 25990000, 'Large cover screen.', 'https://images.indianexpress.com/2023/07/samsung-galaxy-z-flip-5.jpg'),
                         ('Motorola Razr 40 Ultra', 23990000, 'Largest external display.', 'https://m.media-amazon.com/images/I/617WN7I3E4L._SL1200_.jpg'),
                         ('Google Pixel 8a', 12990000, 'The AI phone at affordable price.', 'https://www.smartprix.com/bytes/wp-content/uploads/2023/10/Google-Pixel-8a-5K-renders-Smartprix-Exclusive-2-1-scaled.jpg'),
                         ('iPhone 13', 13990000, 'Still a great value choice.', 'https://www.apple.com/newsroom/images/product/iphone/geo/Apple_iPhone-13-Pro_iPhone-13-Pro-Max_GEO_09142021_inline.jpg.large_2x.jpg'),
                         ('Samsung Galaxy A55', 9990000, 'Awesome design, nightography.', 'https://m.media-amazon.com/images/I/718xfCeNk4L._AC_.jpg'),
                         ('Xiaomi Redmi Note 13 Pro', 7990000, '200MP OIS camera.', 'https://m.media-amazon.com/images/I/71LCkAy9feL._AC_.jpg'),
                         ('Tecno Camon 30', 6990000, 'Fashionable design.', 'https://cdn.kalvo.com/uploads/img/gallery/60235-tecno-camon-30-4.jpg')
) AS v(name, price, descr, img)
WHERE c.category_name = 'Smartphone';

-- --- 2. LAPTOPS ---
INSERT INTO products (product_name, price, description, image, stock_quantity, is_active, category_id)
SELECT v.name, v.price, v.descr, v.img, 30, TRUE, c.category_id
FROM categories c
         CROSS JOIN (VALUES
                         ('MacBook Pro 14 M3', 45990000, 'Mind-blowing performance.', 'https://www.notebookcheck.net/fileadmin/Notebooks/Apple/MacBook_Pro_14_2023_M3/IMG_1048.JPG'),
                         ('MacBook Air M3 13"', 27990000, 'Lean. Mean. M3 machine.', 'https://www.notebookcheck.net/fileadmin/Notebooks/Apple/MacBook_Air_13_M3_10C_GPU/IMG_2758.JPG'),
                         ('Dell XPS 13 Plus', 41990000, 'Futuristic design.', 'https://images.olx.com.pk/thumbnails/594206499-800x600.jpeg'),
                         ('Dell XPS 15', 55990000, 'Power and portability.', 'https://s.yimg.com/os/creatr-uploaded-images/2022-06/5e7ba460-e8c7-11ec-bcfd-246be6e3440b'),
                         ('HP Spectre x360', 38990000, '2-in-1 convertible.', 'https://jp.ext.hp.com/content/dam/jp-ext-hp-com/jp/ja/ec/notebooks/personal/spectre_x360_14_ea0000/images/move3_full.jpg'),
                         ('Lenovo ThinkPad X1 Carbon', 42990000, 'Business powerhouse.', 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?auto=format&fit=crop&w=600&q=80'),
                         ('Asus ROG Zephyrus G14', 39990000, 'Gaming laptop.', 'https://naxtortech.net/wp-content/uploads/2025/02/ASUS-ROG-Zephyrus-G14.png'),
                         ('Razer Blade 14', 59990000, 'Ultra-thin gaming.', 'https://i.pcmag.com/imagery/reviews/077JN1N27RHQ3sZFRkgLGPY-3.jpg'),
                         ('MSI Raider GE78', 65990000, 'Desktop performance.', 'https://cdn.mos.cms.futurecdn.net/yzEAYGSdLcAUnXf84ESYpf.jpg'),
                         ('Acer Swift Go 14', 19990000, 'OLED display.', 'https://laptopmedia.com/wp-content/uploads/2024/01/acer-swift-go-14-sfg14-72-72t-with-fingerprint-with-backlit-wallpaper-logo-pure-silver-03.tif-custom-scaled-e1706627661215.jpg'),
                         ('LG Gram 17', 35990000, 'Impossibly lightweight.', 'https://pisces.bbystatic.com/image2/BestBuy_US/images/products/a676b547-1903-4d41-ab7a-f2005690b653.jpg'),
                         ('Microsoft Surface Laptop 7', 32990000, 'Next-gen AI PC.', 'https://www.trustedreviews.com/wp-content/uploads/sites/7/2024/05/DSCF1172-scaled.jpeg'),
                         ('Microsoft Surface Pro 11', 29990000, 'Tablet PC.', 'https://cdn-dynmedia-1.microsoft.com/is/image/microsoftcorp/surface-pro-11th-edition-color-og-twitter-image?scl=1'),
                         ('Asus Zenbook 14 OLED', 24990000, 'Premium thin and light.', 'https://laptopmedia.com/wp-content/uploads/2021/09/2-6-e1630683013268.jpg'),
                         ('Lenovo Legion 7i', 52990000, 'Top-tier gaming.', 'https://sm.pcmag.com/t/pcmag_au/review/l/lenovo-leg/lenovo-legion-7i-gen-7_cpfu.1920.jpg'),
                         ('HP Omen 16', 31990000, 'Tempest Cooling.', 'https://www.omen.com/content/dam/sites/omen/worldwide/laptops/2023-omen-16-intel/ImageMain32x.png'),
                         ('Dell Alienware m16', 48990000, 'Massive power.', 'https://www.notebookcheck.com/fileadmin/Notebooks/News/_nc3/Dell_Alienware_m16_ces_2023.jpg'),
                         ('MacBook Pro 16 M3 Max', 89990000, 'Advanced Mac.', 'https://cdn.mos.cms.futurecdn.net/XSDK6dZaw8uUMCrJDmiJqS.jpg'),
                         ('Acer Predator Helios', 45990000, 'Superior cooling.', 'https://m.media-amazon.com/images/I/819TJSGmYOL._AC_SL1500_.jpg'),
                         ('Gigabyte Aero 16', 49990000, '4K OLED for creators.', 'https://www.gigabyte.com/FileUpload/Global/KeyFeature/2309/innergigabyteimages/kv.jpg'),
                         ('Framework Laptop 13', 25990000, 'Repairable.', 'https://www.techpowerup.com/img/Q2vlX2vgJ5FeKQxH.jpg'),
                         ('Huawei MateBook X Pro', 36990000, 'Elegant design.', 'https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/pdp/pc/matebook-x-pro-2021/imgs/huawei-matebook-x-pro-2021-kv02.png'),
                         ('Samsung Galaxy Book4 Ultra', 55990000, 'Connected Galaxy.', 'https://www.notebookcheck.org/fileadmin/Notebooks/Samsung/Galaxy_Book4_Ultra/IMG_2948.JPG'),
                         ('Lenovo Yoga 9i', 37990000, 'Comfort edge.', 'https://www.notebookcheck.nl/fileadmin/Notebooks/News/_nc4/header-09_Yoga_9i_2-in-1_14_9_Luna_Grey_Laptop_mode_3Q_front_facing_left_elevated.jpg'),
                         ('Asus Vivobook S 15', 18990000, 'Snapdragon power.', 'https://laptopmedia.com/wp-content/uploads/2023/03/15-1-e1679912127532.jpg')
) AS v(name, price, descr, img)
WHERE c.category_name = 'Laptop';

-- --- 3. SMARTWATCHES ---
INSERT INTO products (product_name, price, description, image, stock_quantity, is_active, category_id)
SELECT v.name, v.price, v.descr, v.img, 40, TRUE, c.category_id
FROM categories c
         CROSS JOIN (VALUES
                         ('Apple Watch Series 9', 10990000, 'Smarter. Brighter.', 'https://www.apple.com/newsroom/images/2023/09/apple-introduces-the-advanced-new-apple-watch-series-9/article/Apple-Watch-S9-graphite-stainless-steel-FineWoven-Magenetic-Link-green-230912_inline.jpg.large_2x.jpg'),
                         ('Apple Watch Ultra 2', 21990000, 'Ultimate sports watch.', 'https://m.media-amazon.com/images/I/81vLYHVwqjL.jpg'),
                         ('Samsung Galaxy Watch 6', 6990000, 'Classic bezel.', 'https://helios-i.mashable.com/imagery/reviews/00fwi01IuVAgd17IcipNO6T/images-1.fill.size_2000x1125.v1694005122.jpg'),
                         ('Samsung Galaxy Watch 5 Pro', 8990000, 'For adventurers.', 'https://www.mobiledokan.com/media/samsung-galaxy-watch5-pro-black-titanium-image.webp'),
                         ('Garmin Fenix 7 Pro', 19990000, 'Solar charging.', 'https://m.media-amazon.com/images/I/71hkemCxOLL._AC_SL1500_.jpg'),
                         ('Garmin Epix Gen 2', 22990000, 'Premium AMOLED.', 'https://cdn.road.cc/sites/default/files/2022-garmin-epix-premium-active-smartwatch-face-detail.jpg'),
                         ('Google Pixel Watch 2', 8990000, 'Health by Fitbit.', 'https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6559/6559637_sd.jpg'),
                         ('Fitbit Charge 6', 3990000, 'Advanced tracking.', 'https://m.media-amazon.com/images/I/61wn2jfhBkL.jpg'),
                         ('Xiaomi Watch S3', 2990000, 'Interchangeable bezels.', 'https://images.frandroid.com/wp-content/uploads/2024/02/xiaomi-watch-s3-frandroid-2024.png'),
                         ('Huawei Watch GT 4', 5990000, 'Fashion forward.', 'https://static1.pocketnowimages.com/wordpress/wp-content/uploads/2023/09/pbi-huawei-watch-gt-4-rainforest-green-gmt.png'),
                         ('Amazfit GTR 4', 4590000, 'Smart fitness.', 'https://techcart.com.au/wp-content/uploads/2023/04/73007-Amazfit-GTR-4-Global-A2166-Brown.png'),
                         ('Amazfit T-Rex Ultra', 9990000, 'Ultimate outdoor.', 'https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6547/6547942_sd.jpg'),
                         ('Suunto Race', 11990000, 'Performance watch.', 'https://suunto.com.hk/cdn/shop/files/suunto-race-titanium-charcoal-perspective-1280x1280px.jpg'),
                         ('Oppo Watch X', 7990000, 'Powered by Wear OS.', 'https://image.oppo.com/content/dam/oppo/common/mkt/v2-2/watch-x-en/specs/watch-x.png'),
                         ('Casio G-Shock Move', 9990000, 'Toughness meets smart.', 'https://gshock.casio.com/content/casio/locales/us/en/brands/gshock/products/gshock-move/_jcr_content/root/responsivegrid/container_107153019/container_749550155__1025000528/content_panel_202104/image.casiocoreimg.jpeg'),
                         ('Polar Vantage V3', 13990000, 'Biosensing.', 'https://www.polar.com/img/static/vantage-v3/explore/call-to-action-img-l.webp'),
                         ('Apple Watch SE 2', 6990000, 'Essential features.', 'https://tse4.mm.bing.net/th/id/OIP.aIMhynWvAFEhsL0K_vWTmAHaFs?pid=Api&h=220&P=0')
) AS v(name, price, descr, img)
WHERE c.category_name = 'Smartwatch';

-- --- 4. HEADPHONES ---
INSERT INTO products (product_name, price, description, image, stock_quantity, is_active, category_id)
SELECT v.name, v.price, v.descr, v.img, 60, TRUE, c.category_id
FROM categories c
         CROSS JOIN (VALUES
                         ('Sony WH-1000XM5', 8490000, 'Industry leading noise canceling.', 'https://fdn.gsmarena.com/imgroot/news/22/05/sony-wh-1000xm5/inline/-1200/gsmarena_001.jpg'),
                         ('Sony WF-1000XM5', 6990000, 'Best noise canceling earbuds.', 'https://m.media-amazon.com/images/I/61YgQ4faTaL._AC_SL1500_.jpg'),
                         ('AirPods Pro 2 USB-C', 5990000, 'Rebuilt from the sound up.', 'https://i.ytimg.com/vi/5o_qtPuf6Kw/maxresdefault.jpg'),
                         ('AirPods Max', 13990000, 'High-fidelity audio.', 'https://www.apple.com/v/airpods-max/h/images/overview/product-stories/hifi-sound/modal/audio_bc_driver__fsdo6q8fwk2u_large.png'),
                         ('Bose QuietComfort Ultra', 9990000, 'World-class noise cancellation.', 'https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6554/6554464_sd.jpg'),
                         ('Sennheiser Momentum 4', 8990000, '60h battery life.', 'https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6570/6570494cv15d.jpg'),
                         ('Sennheiser Momentum TW3', 5990000, 'True wireless perfection.', 'https://www.gearpatrol.com/wp-content/uploads/sites/2/2022/06/sennheiser-momentum-review-lead-1656435940-jpg.webp'),
                         ('JBL Tour One M2', 6490000, 'True adaptive ANC.', 'https://d21buns5ku92am.cloudfront.net/68766/images/439695-JBL%20Tour%20One%20M2%20Black-8ab6ad-large-1660833924.png'),
                         ('Marshall Major IV', 3990000, 'Iconic design.', 'https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6436/6436770_sd.jpg'),
                         ('Beats Studio Pro', 7990000, 'Premium wireless.', 'https://www.beatsbydre.com/content/dam/beats/web/product/headphones/studiopro-wireless/pdp/studiopro-pdp-p13.png.large.2x.png'),
                         ('Samsung Galaxy Buds 2 Pro', 3990000, '24-bit Hi-Fi audio.', 'https://i5.walmartimages.com/seo/Samsung-Galaxy-Buds2-Pro-True-Wireless-Earbud-Headphones-Graphite-SM-R510NZAAXAR_c0579521-06e2-4d0d-89f9-448f20cb3bd7.2e510b522df2fed371567b245676fa0f.jpeg'),
                         ('Hifiman Sundara', 7990000, 'Planar magnetic.', 'https://store.hifiman.com/media/catalog/product/s/u/sundara-1.jpg')
) AS v(name, price, descr, img)
WHERE c.category_name = 'Headphone';

-- --- 5. ELECTRONIC ACCESSORIES ---
INSERT INTO products (product_name, price, description, image, stock_quantity, is_active, category_id)
SELECT v.name, v.price, v.descr, v.img, 100, TRUE, c.category_id
FROM categories c
         CROSS JOIN (VALUES
                         ('Logitech MX Master 3S', 2590000, 'Performance wireless mouse.', 'https://tse4.mm.bing.net/th/id/OIP.5WgFPsz6Cl7js5P7hdYqjAHaF6?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('Logitech MX Keys S', 2990000, 'Wireless illuminated keyboard.', 'https://sm.pcmag.com/t/pcmag_uk/review/l/logitech-m/logitech-mx-keys-s_mykj.1920.jpg'),
                         ('Anker 737 Power Bank', 3990000, '140W fast charging.', 'https://m.media-amazon.com/images/I/71Linf+GHuL.jpg'),
                         ('Anker Prime GaN Charger', 2490000, '100W compact.', 'https://tse1.mm.bing.net/th/id/OIP.zjg-tW1AhJBjAmlWDFqyyAHaHa?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('Samsung T7 SSD 1TB', 2990000, 'Portable SSD.', 'https://c1.neweggimages.com/ProductImageCompressAll1280/20-147-764-V02.jpg'),
                         ('SanDisk Extreme Portable 2TB', 4590000, 'Tough storage.', 'https://m.media-amazon.com/images/I/711-n+US0bL.jpg'),
                         ('Keychron Q1 Pro', 4990000, 'Custom mechanical keyboard.', 'https://tse1.mm.bing.net/th/id/OIP.R2a6CaF5qsjm94DFawC05gHaEK?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('Razer DeathAdder V3', 3590000, 'Esports mouse.', 'https://tse1.mm.bing.net/th/id/OIP.M6wkeP3iFvz8yc-b1KgeeQHaD4?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('SteelSeries Apex Pro', 5590000, 'Fastest keyboard.', 'https://tse2.mm.bing.net/th/id/OIP.-nxH1dvxAzdcJZlIrO73UAHaEK?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('Elgato Stream Deck', 3990000, 'Studio controller.', 'https://tse4.mm.bing.net/th/id/OIP.XlJCNUIiUbkJVj9wOP6oYwHaD4?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('Dell UltraSharp Webcam', 4990000, '4K AI webcam.', 'https://tse3.mm.bing.net/th/id/OIP.NA42Wka7a2R5pT5pgrDVEgHaD4?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('Belkin 3-in-1 Charger', 3990000, 'Wireless charging stand.', 'https://tse4.mm.bing.net/th/id/OIP.DjZIOuyeQJZfPeiIv3jsmwHaE8?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('DJI Osmo Pocket 3', 14990000, 'Gimbal camera.', 'https://tse4.mm.bing.net/th/id/OIP.DjZIOuyeQJZfPeiIv3jsmwHaE8?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('Insta360 X4', 12990000, '360 degree 8K.', 'https://tse4.mm.bing.net/th/id/OIP.T59qnDLyVwKkEH-PccgGCgHaHa?rs=1&pid=ImgDetMain&o=7&rm=3'),
                         ('Peak Design Tech Pouch', 1990000, 'Cable organizer.', 'https://tse4.mm.bing.net/th/id/OIP.BZMwT_DOrMFWcLeu_yRA2gHaHa?rs=1&pid=ImgDetMain&o=7&rm=3')
) AS v(name, price, descr, img)
WHERE c.category_name = 'Electronic Accessories';