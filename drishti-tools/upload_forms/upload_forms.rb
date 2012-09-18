require_relative 'read_anc_services_from_xlsx.rb'
require_relative 'read_ancs_from_xlsx.rb'
require_relative 'read_ecs_from_xlsx.rb'

ecs = ECs.new("examples/Munjanhalli.xlsx").ecs
ancs_per_ec = ANCs.new("examples/Munjanhalli.xlsx").ancs_grouped_per_couple
anc_services_per_ec = ANCServices.new("examples/Munjanhalli.xlsx").anc_services_per_ec

puts "Got: ECs: #{ecs.size}. ANCS grouped by EC: #{ancs_per_ec.size}. Services grouped by EC: #{anc_services_per_ec.size}"