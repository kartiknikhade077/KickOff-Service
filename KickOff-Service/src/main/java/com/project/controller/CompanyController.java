package com.project.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.UserSerivceClinet;
import com.project.dto.Company;
import com.project.entity.BOMCategory;
import com.project.entity.BOMCategoryInfo;
import com.project.entity.BOMInfo;
import com.project.entity.CheckListCategory;
import com.project.entity.CheckListCategoryAndItems;
import com.project.entity.CheckListInfo;
import com.project.entity.CheckListItemsInfo;
import com.project.entity.CustomerRequirements;
import com.project.entity.ItemProcess;
import com.project.entity.ItemsImages;
import com.project.entity.KickOff;
import com.project.entity.KickOffItems;
import com.project.entity.MOMEntries;
import com.project.entity.MOMEntriesImages;
import com.project.entity.MOMInfo;
import com.project.repository.*;

@RestController
@RequestMapping("/kickoff")
public class CompanyController {

    private final CheckListCategoryAndItemsRepository checkListCategoryAndItemsRepository;
	
	@Autowired
	private UserSerivceClinet userSerivceClinet;
	
	@Autowired
	private KickOffRepository kickOffRepository;
	
	@Autowired
	private KickOffItemsRepository kickOffItemsRepository;
	
	@Autowired
	private itemProcessRepository itemProcessRepository;
	
	@Autowired
	private ItemImageRepository itemImageRepository;
	
	@Autowired
	private CustomerRequirementsRepository customerRequirementsRepository;
	
	@Autowired
	private KickOffSignatureRepository kickOffSignatureRepository;
	
	@Autowired
	private CheckListInfoRepository checkListInfoRepository;
	
	@Autowired
	private CheckListItemsInfoRepository checkListItemsInfoRepository;
	
	@Autowired
	private CheckListCategoryRepository checkListCategoryRepository;
	
	@Autowired
	private MOMEntriesInfoRepositories momEntriesInfoRepositories;
	
	@Autowired
	private MOMEntriesImagesRepository momEntriesImagesRepository;
	
	@Autowired
	private MOMInfoRepository momInfoRepository;
	
	@Autowired
	private BOMCategoryRepository bomCategoryRepository;
	
	@Autowired
	private BOMInfoRepository bomInfoRepository;
	
	@Autowired
	private BOMCategoryInfoRepository bomCategoryInfoRepository;
	
	Company company;

    CompanyController(CheckListCategoryAndItemsRepository checkListCategoryAndItemsRepository) {
        this.checkListCategoryAndItemsRepository = checkListCategoryAndItemsRepository;
    }

	@ModelAttribute
	public void companyDetails() {

		company = userSerivceClinet.getCompanyInfo();

	}
	
	@GetMapping
	public String  testing() {
		return "Testing";
	}
	
	@PostMapping("/createKickOffInfo")
	public ResponseEntity<?> createKickOffInfo(@RequestBody KickOff kickOff) {

		try {
			kickOff.setCreatedDateTime(LocalDateTime.now());
			kickOff.setCompanyId(company.getCompanyId());
			kickOffRepository.save(kickOff);
			return ResponseEntity.ok(kickOff);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PostMapping("/saveKickOffItems")
	public ResponseEntity<?> saveKickOffItems(@RequestBody List<KickOffItems> items) {

		try {
			
			List<KickOffItems> savedItems = kickOffItemsRepository.saveAll(items);
			
			
			 for (KickOffItems savedItem : savedItems) {
		            if (savedItem.getImageList() != null && !savedItem.getImageList().isEmpty()) {
		                for (String base64Image : savedItem.getImageList()) {
		                   ItemsImages  image = new ItemsImages();
		                    image.setItemId(savedItem.getItemId());
		                    image.setImage(Base64.getDecoder().decode(base64Image));
		                    itemImageRepository.save(image);
		                }
		            }
		        }
			
			return ResponseEntity.ok(items);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PostMapping("/saveKickOffItemsProccess")
	public ResponseEntity<?> saveKickOffItemsProccess(@RequestBody List<ItemProcess> items) {

		try {
			
			List<ItemProcess> saved = itemProcessRepository.saveAll(items);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PostMapping("/saveCustomerRequirements")
	public ResponseEntity<?> saveCustomerRequirements(@RequestBody List<CustomerRequirements> customerRequirements) {

		try {
			
			List<CustomerRequirements> saved = customerRequirementsRepository.saveAll(customerRequirements);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@PostMapping("/saveKickOffSignature")
	public ResponseEntity<?> saveKickOffSignature(@RequestBody List<KickOffSignature> kickOffSignature) {

		try {
			
			List<KickOffSignature> saved = kickOffSignatureRepository.saveAll(kickOffSignature);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@GetMapping("/getKickOffInfo/{kickOffId}")
	public ResponseEntity<?> getKickOffInfo(@PathVariable String kickOffId) {

		try {

			KickOff kickOff = kickOffRepository.findBykickOffId(kickOffId);
			List<KickOffItems> kickOffItemsList = kickOffItemsRepository.findByKickOffId(kickOffId);
			List<ItemProcess> itemProcessList = itemProcessRepository.findByKickOffId(kickOffId);
			List<CustomerRequirements> requirementList = customerRequirementsRepository.findByKickOffId(kickOffId);
			List<KickOffSignature> listofSingnature = kickOffSignatureRepository.findByKickOffId(kickOffId);
			
			List<String> itemIds = kickOffItemsList.stream()
			        .map(KickOffItems::getItemId)
			        .collect(Collectors.toList());

			// Step 2: Fetch all images with itemId IN (...)
			List<ItemsImages> allImages = itemImageRepository.findByItemIdIn(itemIds);
			
			// Step 3: Group images by itemId
			Map<String, List<String>> imagesByItemId = allImages.stream()
			        .collect(Collectors.groupingBy(
			            ItemsImages::getItemId,
			            Collectors.mapping(img -> Base64.getEncoder().encodeToString(img.getImage()), Collectors.toList())
			        ));

			// Step 4: Set imageList into KickOffItems
			for (KickOffItems item : kickOffItemsList) {
			    List<String> imageList = imagesByItemId.getOrDefault(item.getItemId(), new ArrayList<>());
			    item.setImageList(imageList);
			}

			Map<String, Object> data = new HashMap<>();
			data.put("kickOffInfo", kickOff);
			data.put("kickOffItemsList", kickOffItemsList);
			data.put("itemProcessList", itemProcessList);
			data.put("requirementList", requirementList);
			data.put("listofSingnature", listofSingnature);
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@PutMapping("/updateKickOffInfo")
	public ResponseEntity<?> updateKickOffInfo(@RequestBody KickOff kickOff) {

		try {
			kickOff.setCompanyId(company.getCompanyId());
			kickOffRepository.save(kickOff);
			return ResponseEntity.ok(kickOff);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@PutMapping("/updateItem")
	public ResponseEntity<?> updateItem(@RequestBody KickOffItems kickOffItem) {

		try {
			kickOffItemsRepository.save(kickOffItem);
			return ResponseEntity.ok(kickOffItem);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteItem/{itemId}")
	public ResponseEntity<?> deleteItem(@PathVariable String itemId) {

		try {
		
			kickOffItemsRepository.deleteById(itemId);
			return ResponseEntity.ok("Item Deleted ");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	
	@PostMapping(value="/addItemImage", consumes = {"multipart/form-data"})
	public ResponseEntity<?> addItemImage(@RequestPart(value = "image", required = false) MultipartFile image ,
			@RequestPart("itemId") String itemId) {

		try {
			ItemsImages itemImage=new ItemsImages();
			itemImage.setImage(image.getBytes());
			itemImage.setItemId(itemId);
			itemImageRepository.save(itemImage);
			return ResponseEntity.ok(itemImage);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@DeleteMapping("/deleteItemImage/{imageId}")
	public ResponseEntity<?> deleteItemImage(@PathVariable String imageId) {

		try {
		
			itemImageRepository.deleteById(imageId);
			return ResponseEntity.ok("Image Deleted ");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@PutMapping("/updateKickOffItemsProccess")
	public ResponseEntity<?> updateKickOffItemsProccess(@RequestBody List<ItemProcess> items) {

		try {
			
			List<ItemProcess> saved = itemProcessRepository.saveAll(items);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteItemProcess/{processId}")
	public ResponseEntity<?> deleteItemProcess(@PathVariable String processId) {

		try {
		
			itemProcessRepository.deleteById(processId);
			return ResponseEntity.ok("Process Deleted ");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateCustomerRequirements")
	public ResponseEntity<?> updateCustomerRequirements(@RequestBody List<CustomerRequirements> customerRequirements) {

		try {
			
			List<CustomerRequirements> saved = customerRequirementsRepository.saveAll(customerRequirements);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateKickOffSignature")
	public ResponseEntity<?> updateKickOffSignature(@RequestBody List<KickOffSignature> kickOffSignature) {

		try {
			
			List<KickOffSignature> saved = kickOffSignatureRepository.saveAll(kickOffSignature);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getAllKickOffs/{page}/{size}")
	public ResponseEntity<?> getAllKickOffs(@PathVariable int page,@PathVariable int size, @RequestParam(defaultValue = "") String projectName) {
		try {
			
			
			Map<String ,Object> data=new HashMap<String , Object>();
			 Pageable pageable = PageRequest.of(page, size, Sort.by("createdDateTime").descending());
			
		        Page<KickOff> kickOffPage = kickOffRepository.findByCompanyIdAndProjectNameContainingIgnoreCase(company.getCompanyId(),projectName, pageable);
		        List<KickOff> kickOffList = kickOffPage.getContent();
		        data.put("kickOffList", kickOffList);
		        data.put("totalPages", kickOffPage.getTotalPages());
		        data.put("currentPage", kickOffPage.getNumber());
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@PostMapping("/createCheckList")
	public ResponseEntity<?> createCheckList(@RequestBody Map<String, Object> request) {
	    try {
	        // Step 1: Extract "checkListInfo" map and convert to CheckList object
	        ObjectMapper mapper = new ObjectMapper();
	        
	        // Convert to CheckList
	        CheckListInfo checkList = mapper.convertValue(request.get("checkListInfo"), CheckListInfo.class);
	        checkList.setCreatedDateTime(LocalDateTime.now());
	        checkList.setCompanyId(company.getCompanyId());

	        // Save the checklist
	        CheckListInfo savedCheckList = checkListInfoRepository.save(checkList);
	        String checkListId = savedCheckList.getCheckListId();

	        // Step 2: Extract "checkListItems" list and convert to List<CheckListItem>
	        List<Map<String, Object>> itemsList = (List<Map<String, Object>>) request.get("checkListItems");

	        List<CheckListItemsInfo> itemsToSave = new ArrayList<>();
	        for (Map<String, Object> itemMap : itemsList) {
	        	CheckListItemsInfo item = mapper.convertValue(itemMap, CheckListItemsInfo.class);
	            item.setCheckListId(checkListId); // Set foreign key
	            itemsToSave.add(item);
	        }

	        checkListItemsInfoRepository.saveAll(itemsToSave);

	        return ResponseEntity.ok("Checklist and items created successfully.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error: " + e.getMessage());
	    }
	}
	
	
	@PutMapping("/updateCheckList")
	public ResponseEntity<?> updateCheckList(@RequestBody Map<String, Object> request) {
	    try {
	        // Step 1: Extract "checkListInfo" map and convert to CheckList object
	        ObjectMapper mapper = new ObjectMapper();
	        
	        // Convert to CheckList
	        CheckListInfo checkList = mapper.convertValue(request.get("checkListInfo"), CheckListInfo.class);

	        // Save the checklist
	        checkList.setCreatedDateTime(LocalDateTime.now());
	        checkList.setCompanyId(company.getCompanyId());
	        CheckListInfo savedCheckList = checkListInfoRepository.save(checkList);
	        String checkListId = savedCheckList.getCheckListId();

	        // Step 2: Extract "checkListItems" list and convert to List<CheckListItem>
	        List<Map<String, Object>> itemsList = (List<Map<String, Object>>) request.get("checkListItems");

	        List<CheckListItemsInfo> itemsToSave = new ArrayList<>();
	        for (Map<String, Object> itemMap : itemsList) {
	        	CheckListItemsInfo item = mapper.convertValue(itemMap, CheckListItemsInfo.class);
	            itemsToSave.add(item);
	        }

	        checkListItemsInfoRepository.saveAll(itemsToSave);

	        return ResponseEntity.ok("Checklist and items created successfully.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error: " + e.getMessage());
	    }
	}
	
	
	@GetMapping("/getCheckListData/{checkListId}")
	public ResponseEntity<?> getCheckListData(@PathVariable String checkListId) {

		try {
			
			Map<String,Object> data=new HashMap<>();
			
			CheckListInfo checkListInfo=checkListInfoRepository.findByCheckListId(checkListId);
			List<CheckListItemsInfo> checkListItemsInfo=checkListItemsInfoRepository.findByCheckListId(checkListId);
			
			data.put("checkListInfo", checkListInfo);
			data.put("checkListItemsList",checkListItemsInfo);
			
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@GetMapping("/getAllCheckList/{page}/{size}")
	public ResponseEntity<?> getAllCheckList(@PathVariable int page,@PathVariable int size, @RequestParam(defaultValue = "") String projectName) {
		try {
			
			
			Map<String ,Object> data=new HashMap<String , Object>();
			 Pageable pageable = PageRequest.of(page, size, Sort.by("createdDateTime").descending());
			
		        Page<CheckListInfo> checkListInfoPage = checkListInfoRepository.findByCompanyIdAndProjectNameContainingIgnoreCase(company.getCompanyId(),projectName, pageable);
		        List<CheckListInfo> allCheckList = checkListInfoPage.getContent();
		        data.put("allCheckList", allCheckList);
		        data.put("totalPages", checkListInfoPage.getTotalPages());
		        data.put("currentPage", checkListInfoPage.getNumber());
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@DeleteMapping("/deleteCheckListItem/{itemId}")
    public ResponseEntity<?> deleteCheckListItem(@PathVariable String itemId) {
        try {
            if (!checkListCategoryAndItemsRepository.existsById(itemId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found with ID: " + itemId);
            }

            checkListCategoryAndItemsRepository.deleteById(itemId);
            return ResponseEntity.ok("Checklist item deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting item: " + e.getMessage());
        }
    }
	
	
	@PostMapping("/createCheckListCategory")
	public ResponseEntity<?> createCheckListCategory(@RequestBody CheckListCategory checkListCategory) {

		try {
			checkListCategory.setCompanyId(company.getCompanyId());
			
			return ResponseEntity.ok(checkListCategoryRepository.save(checkListCategory));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateCheckListCategory")
	public ResponseEntity<?> updateCheckListCategory(@RequestBody List<CheckListCategory> checkListCategory) {

		try {
			List<CheckListCategory>  saved=checkListCategoryRepository.saveAll(checkListCategory);
			
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getCheckListCategory")
	public ResponseEntity<?> getCheckListCategory() {

		try {
			List<CheckListCategory>  categoryList=checkListCategoryRepository.findByCompanyIdOrderBySequence(company.getCompanyId());
			
			return ResponseEntity.ok(categoryList);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@PostMapping("/createCheckListCategoryWithItem")
	public ResponseEntity<?> createCheckListCategoriesWithItems(@RequestBody List<CheckListCategoryAndItems> items) {
	    try {
	        for (CheckListCategoryAndItems item : items) {
	            item.setCompanyId(company.getCompanyId());
	        }

	        return ResponseEntity.ok(checkListCategoryAndItemsRepository.saveAll(items));
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	}

	
	
	@PutMapping("/updateCheckListCategoryWithItem")
	public ResponseEntity<?> updateCheckListCategoryWithItem(@RequestBody List<CheckListCategoryAndItems> checkListCategoryAndItems) {

		try {
			for (CheckListCategoryAndItems item : checkListCategoryAndItems) {
				item.setCompanyId(company.getCompanyId());
			}
			
			return ResponseEntity.ok(checkListCategoryAndItemsRepository.saveAll(checkListCategoryAndItems));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getCheckListCategoryAndItem")
	public ResponseEntity<?> getCheckListCategoryAndItem() {

		try {
			List<CheckListCategoryAndItems>  itemListList=checkListCategoryAndItemsRepository.findByCompanyIdOrderBySequence(company.getCompanyId());
			
			return ResponseEntity.ok(itemListList);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getWorkOrderByEmployeeId/{employeeId}")
	public ResponseEntity<?> getWorkOrderByEmployeeId(@PathVariable String employeeId) {

		try {
			List<ItemProcess>  itemProcessList=itemProcessRepository.findByEmployeeId(employeeId);
			
			return ResponseEntity.ok(itemProcessList);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteCheckListCategoryById/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") String categoryId) {
        if (checkListCategoryRepository.existsById(categoryId)) {
            checkListCategoryRepository.deleteById(categoryId);
            return ResponseEntity.ok("Category deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Category not found with ID: " + categoryId);
        }
    }
	
	
	@PostMapping("/createMOM")
	public ResponseEntity<?> createMOM(@RequestBody Map<String, Object> request) {

		try {
			 ObjectMapper mapper = new ObjectMapper();
		        
		        // Convert to CheckList
		        MOMInfo momInfo = mapper.convertValue(request.get("momInfo"), MOMInfo.class);
		        
		        momInfo.setCompanyId(company.getCompanyId());
		        MOMInfo saveMOMInfo=  momInfoRepository.save(momInfo);
		        
		        

		        // Step 2: Extract "checkListItems" list and convert to List<CheckListItem>
		        List<Map<String, Object>> momEntriesList = (List<Map<String, Object>>) request.get("momEntries");
		        

		     //   List<MOMEntries> itemsToSave = new ArrayList<>();
		        for (Map<String, Object> itemMap : momEntriesList) {
		        	MOMEntries item = mapper.convertValue(itemMap, MOMEntries.class);
		        	item.setMomId(saveMOMInfo.getMomId());
		           	MOMEntries savedMomEntries=  momEntriesInfoRepositories.save(item);
		            saveImages(savedMomEntries.getMomEntryId(), item.getIllustrationImages(),item.getCorrectedImages());
			        }

			     

		        return ResponseEntity.ok("MOM created Successfully");
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	

		     
	
	private void saveImages(String momEntryId, List<String> illustrationImages, List<String> correctedImages) {
	    try {
	        if (illustrationImages != null) {
	            for (String base64Image : illustrationImages) {
	            	
	                saveImage(momEntryId, base64Image, "illustration");
	            }
	        }

	        if (correctedImages != null) {
	            for (String base64Image : correctedImages) {
	                saveImage(momEntryId, base64Image, "corrected");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void saveImage(String momEntryId, String base64Data, String imageType) {
	    try {
	        String base64Image = base64Data.contains(",") ? base64Data.split(",")[1] : base64Data;
	        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

	        MOMEntriesImages image = new MOMEntriesImages();
	        image.setMomEntryId(momEntryId);
	        image.setImage(imageBytes);
	        image.setType(imageType);

	        momEntriesImagesRepository.save(image);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@PutMapping("/updateMOMInfo")
	public ResponseEntity<?> updateMOMInfo(@RequestBody MOMInfo momInfo) {

		try {
		
			momInfo.setCompanyId(company.getCompanyId());
			
			return ResponseEntity.ok(momInfoRepository.save(momInfo));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	//use this api while adding th mom entries section
	
	@PostMapping("/addMOMEntry")
	public ResponseEntity<?> addMOMEntry(@RequestBody List<MOMEntries> momEntries) {

		try {
			
			 for (MOMEntries item : momEntries) {
	
		        	MOMEntries savedMomEntries=  momEntriesInfoRepositories.save(item);
		            saveImages(savedMomEntries.getMomEntryId(), item.getIllustrationImages(),item.getCorrectedImages());
			        }
			return ResponseEntity.ok("All MOM entries saved successfully.");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@PutMapping("/updateMOMEntry")
	public ResponseEntity<?> v(@RequestBody List<MOMEntries> momEntries) {

		try {
			 for (MOMEntries item : momEntries) {
					
		        	MOMEntries savedMomEntries=  momEntriesInfoRepositories.save(item);
		            saveImages(savedMomEntries.getMomEntryId(), item.getIllustrationImages(),item.getCorrectedImages());
			        }
			return ResponseEntity.ok("All MOM entries Updated successfully.");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@DeleteMapping("/deleteSingleMOMEntries/{momEntryId}")
	public ResponseEntity<?> deleteSingleMOMEntries(@PathVariable String momEntryId) {

		try {
		
		  momEntriesInfoRepositories.deleteById(momEntryId); 
		  
		 momEntriesImagesRepository.deleteByMomEntryId(momEntryId);
			
			return ResponseEntity.ok("Deleted MOM Entry ");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	//this is used to remove section
	@Transactional
	@DeleteMapping("/deleteBulkEntries")
	public ResponseEntity<?> deleteBulkEntries(@RequestBody Map<String, Object> request) {
	    try {
	        String workOrderNo = request.get("workOrderNo").toString();

	        // Delete MOM Entries by Work Order No
	        momEntriesInfoRepositories.deleteByWorkOrderNo(workOrderNo);

	        // Extract momEntryIds as List
	        List<String> momEntryIds = (List<String>) request.get("momEntryIds");

	        // Delete all MOMEntryImages by momEntryIds
	        momEntriesImagesRepository.deleteBulkMomEntryIds(momEntryIds);

	        return ResponseEntity.ok("Deleted MOM Entries and related images.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error: " + e.getMessage());
	    }
	}

	
	@DeleteMapping("/deleteMOMImage/{imageId}")
	public ResponseEntity<?> deleteMOMImage(@PathVariable String imageId) {

		try {
		
			momEntriesImagesRepository.deleteById(imageId);
			
			return ResponseEntity.ok("Deleted MOM Entry ");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
  
	
	@GetMapping("/getMOMList/{page}/{size}")
	public ResponseEntity<?> getMOMList(@PathVariable int page,@PathVariable int size, @RequestParam(defaultValue = "") String projectName) {
		try {
			
			
			Map<String ,Object> data=new HashMap<String , Object>();
			 Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
			
		        Page<MOMInfo> MOMInfos = momInfoRepository.findByCompanyIdAndProjectNameContainingIgnoreCase(company.getCompanyId(),projectName, pageable);
		        List<MOMInfo> MOMInfosList = MOMInfos.getContent();
		        data.put("MOMInfosList", MOMInfosList);
		        data.put("totalPages", MOMInfos.getTotalPages());
		        data.put("currentPage", MOMInfos.getNumber());
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getItemNoByProjectId/{projectId}")
	public ResponseEntity<?> getItemNoByProjectId(@PathVariable String projectId) {

		try {
		
		   List<Integer> itemNo=kickOffItemsRepository.findItemNoByProjectId(projectId);
			
			return ResponseEntity.ok(itemNo);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@GetMapping("/getPartNameByItemNo/{itemNo}")
	public ResponseEntity<?> getPartNameByItemNo(@PathVariable int itemNo) {

		try {
		
		   String partName=kickOffItemsRepository.findPartNameByItemNo(itemNo);
			
			return ResponseEntity.ok(partName);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getWorkOrderNumberByItemNo/{itemNo}")
	public ResponseEntity<?> getWorkOrderNumber(@PathVariable Integer itemNo) {

		try {
		
		   List<String> workOrderNumbers=itemProcessRepository.findWorkOrderNumberByItemNo(itemNo);
			
			return ResponseEntity.ok(workOrderNumbers);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PostMapping("/createBOMCategory")
	public ResponseEntity<?> createBOMCategory(@RequestBody List<BOMCategory> BOMCategories) {

		try {
			List<BOMCategory> updatedBOMCategory=new ArrayList<>();
			for(BOMCategory category: BOMCategories) {
				
				category.setCompanyId(company.getCompanyId());
				updatedBOMCategory.add(category);
			}
			
			return ResponseEntity.ok(ResponseEntity.ok(bomCategoryRepository.saveAll(updatedBOMCategory)));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateBOMCategory")
	public ResponseEntity<?> updateBOMCategory(@RequestBody List<BOMCategory> BOMCategories) {

		try {
			List<BOMCategory> updatedBOMCategory=new ArrayList<>();
			for(BOMCategory category: BOMCategories) {
				
				category.setCompanyId(company.getCompanyId());
				updatedBOMCategory.add(category);
			}
			
			return ResponseEntity.ok(ResponseEntity.ok(bomCategoryRepository.saveAll(updatedBOMCategory)));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@GetMapping("/getCategoryByType/{categoryType}")
	public ResponseEntity<?> getCategoryByType(@PathVariable String categoryType) {

		try {
		
		   List<BOMCategory> BOMCategoryList=bomCategoryRepository.findByCategoryTypeAndCompanyId(categoryType,company.getCompanyId());
			
			return ResponseEntity.ok(BOMCategoryList);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getCategoryByCompanyId")
	public ResponseEntity<?> getCategoryByCompanyId() {

		try {
		
		   List<BOMCategory> BOMCategoryList=bomCategoryRepository.findByCompanyId(company.getCompanyId());
		   
		    Map<String, List<String>> grouped = BOMCategoryList.stream()
		            .filter(BOMCategory::isStatus) // only true status
		            .collect(Collectors.groupingBy(
		            		BOMCategory::getCategoryType,
		                Collectors.mapping(BOMCategory::getCategoryField, Collectors.toList())
		            ));
			
			return ResponseEntity.ok(grouped);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@PostMapping("/createBOM")
	public ResponseEntity<?> createBOM(@RequestBody Map<String, Object> request) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			// Convert to BOM
			BOMInfo bomInfo = mapper.convertValue(request.get("BOMInfo"), BOMInfo.class);
            bomInfo.setCreatedDateTime(LocalDateTime.now());
			bomInfo.setCompanyId(company.getCompanyId());
			BOMInfo savedBOMInfo= bomInfoRepository.save(bomInfo);

			// Step 2: Extract "checkListItems" list and convert to List<CheckListItem>
			List<Map<String, Object>> bomCategoryInfos = (List<Map<String, Object>>) request.get("BOMCategoryInfo");

			List<BOMCategoryInfo> BOMCategoryInfoList = new ArrayList<>();
			for (Map<String, Object> bomCategoryInfosMap : bomCategoryInfos) {
				BOMCategoryInfo category = mapper.convertValue(bomCategoryInfosMap, BOMCategoryInfo.class);
				category.setBomId(savedBOMInfo.getBomId());
				BOMCategoryInfoList.add(category);

			}

			bomCategoryInfoRepository.saveAll(BOMCategoryInfoList);

			return ResponseEntity.ok("BOM Created Successfully");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getBOMInfoById/{bomId}")
	public ResponseEntity<?> getBOMInfoById(@PathVariable String bomId) {

		try {
			Map<String ,Object> data=new HashMap<>();
			
			Optional<BOMInfo> bomInfo=bomInfoRepository.findById(bomId);
			
			List<BOMCategoryInfo> bomInfoCategory=bomCategoryInfoRepository.findByBomId(bomId);
			
			data.put("BOMInfo", bomInfo);
			data.put("BOMInfoCategory", bomInfoCategory);
			
			return ResponseEntity.ok(data);
			

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateBOMInfo")
	public ResponseEntity<?> updateBOMInfo(@RequestBody BOMInfo BOMInfo) {

		try {
			
			BOMInfo.setCompanyId(company.getCompanyId());
			return ResponseEntity.ok(ResponseEntity.ok(bomInfoRepository.save(BOMInfo)));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateBOMCategoryInfo")
	public ResponseEntity<?> updateBOMCategoryInfo(@RequestBody BOMCategoryInfo BOMCategoryInfo) {

		try {
			return ResponseEntity.ok(ResponseEntity.ok(bomCategoryInfoRepository.save(BOMCategoryInfo)));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@DeleteMapping("/deleteBOMCategoryInfo/{bomCategoryInfoId}")
	public ResponseEntity<?> deleteBOMCategoryInfo(@PathVariable String bomCategoryInfoId) {

		try {
		
			bomCategoryInfoRepository.deleteById(bomCategoryInfoId);
			return ResponseEntity.ok("Categaory Deleted Deleted ");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getAllBOMs/{page}/{size}")
	public ResponseEntity<?> getAllBOMs(@PathVariable int page,@PathVariable int size, @RequestParam(defaultValue = "") String projectName) {
		try {
			
			
			Map<String ,Object> data=new HashMap<String , Object>();
			 Pageable pageable = PageRequest.of(page, size, Sort.by("createdDateTime").descending());
			
		        Page<BOMInfo> BOMInfoPage = bomInfoRepository.findByCompanyIdAndProjectNameContainingIgnoreCase(company.getCompanyId(),projectName, pageable);
		        List<BOMInfo> BOMInfoList = BOMInfoPage.getContent();
		        data.put("BOMInfoList", BOMInfoList);
		        data.put("totalPages", BOMInfoPage.getTotalPages());
		        data.put("currentPage", BOMInfoPage.getNumber());
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	


	@Transactional
	@DeleteMapping("/deleteCheckList/{checkListId}")
    public ResponseEntity<String> deleteCheckListWithItems(@PathVariable String checkListId) {
        try {
            Optional<CheckListInfo> optionalCheckList = checkListInfoRepository.findById(checkListId);
            if (optionalCheckList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("CheckList with ID " + checkListId + " not found.");
            }

            // Step 1: Delete associated items
            checkListItemsInfoRepository.deleteByCheckListId(checkListId);

            // Step 2: Delete checklist
            checkListInfoRepository.deleteById(checkListId);

            return ResponseEntity.ok("CheckList and its items deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete CheckList: " + e.getMessage());
        }
    }
	
	@DeleteMapping("/deleteMom/{momId}")
    public ResponseEntity<String> deleteMOM(@PathVariable String momId) {
        try {
            List<MOMEntries> momEntriesList = momEntriesInfoRepositories.findByMomId(momId);
            
            for (MOMEntries momEntry : momEntriesList) {
                List<MOMEntriesImages> imagesList = momEntriesImagesRepository.findByMomEntryId(momEntry.getMomEntryId());
                momEntriesImagesRepository.deleteAll(imagesList);
            }

            momEntriesInfoRepositories.deleteAll(momEntriesList);
            momInfoRepository.deleteById(momId);

            return ResponseEntity.ok("MOMInfo and associated data deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error deleting MOMInfo: " + e.getMessage());
        }
    }
	
	@GetMapping("/getSingleMomById/{momId}")
	public ResponseEntity<?> getSingleMomById(@PathVariable("momId") String momId) {
	    try {
	        // Fetch MOM info
	        MOMInfo momInfo = momInfoRepository.findById(momId)
	                .orElseThrow(() -> new RuntimeException("MOM not found with id: " + momId));

	        // Fetch all entries
	        List<MOMEntries> momEntriesList = momEntriesInfoRepositories.findByMomId(momId);

	        // Prepare list of entry maps
	        List<Map<String, Object>> momEntriesResponse = new ArrayList<>();

	        for (MOMEntries entry : momEntriesList) {
	            Map<String, Object> entryMap = new HashMap<>();
	            entryMap.put("momEntryId", entry.getMomEntryId());
	            entryMap.put("momId", entry.getMomId());
	            entryMap.put("workOrderNo", entry.getWorkOrderNo());
	            entryMap.put("tooleName", entry.getTooleName());
	            entryMap.put("observation", entry.getObservation());
	            entryMap.put("details", entry.getDetails());
	            entryMap.put("correctedPoints", entry.getCorrectedPoints());
	            entryMap.put("responsibleAndTarget", entry.getResponsibleAndTarget());

	            // Illustration Images
	            List<Map<String, String>> illustrationImagesList = momEntriesImagesRepository
	                    .findByMomEntryIdAndType(entry.getMomEntryId(), "illustration")
	                    .stream()
	                    .map(img -> {
	                        Map<String, String> imgMap = new HashMap<>();
	                        imgMap.put("imageId", img.getImagesId());
	                        imgMap.put("image", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(img.getImage()));
	                        return imgMap;
	                    })
	                    .toList();
	            entryMap.put("illustrationImages", illustrationImagesList);

	            // Corrected Images
	            List<Map<String, String>> correctedImagesList = momEntriesImagesRepository
	                    .findByMomEntryIdAndType(entry.getMomEntryId(), "corrected")
	                    .stream()
	                    .map(img -> {
	                        Map<String, String> imgMap = new HashMap<>();
	                        imgMap.put("imageId", img.getImagesId());
	                        imgMap.put("image", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(img.getImage()));
	                        return imgMap;
	                    })
	                    .toList();
	            entryMap.put("correctedImages", correctedImagesList);

	            momEntriesResponse.add(entryMap);
	        }

	        // Final response
	        Map<String, Object> response = new HashMap<>();
	        response.put("momInfo", momInfo);
	        response.put("momEntries", momEntriesResponse);

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error fetching MOM details: " + e.getMessage());
	    }
	}
	
	@DeleteMapping("/deleteBomCategory/{categoryType}")
    public ResponseEntity<String> deleteByCategoryType(@PathVariable String categoryType) {
        try {
            bomCategoryRepository.deleteByCategoryType(categoryType);
            return ResponseEntity.ok("Deleted all BOMCategories with categoryType: " + categoryType);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting category: " + e.getMessage());
        }
    }
	
	@GetMapping("/getItemProcessByWorkOrderNumber/{workOrderNumber}")
	public ResponseEntity<?> updateBOMCategoryInfo(@PathVariable String workOrderNumber) {

		try {
			
			return ResponseEntity.ok(ResponseEntity.ok(itemProcessRepository.findByWorkOrderNumber(workOrderNumber)));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}

}
